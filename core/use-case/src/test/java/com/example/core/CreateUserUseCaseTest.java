package com.example.core;


import com.example.core.factory.FactoryBase;
import com.example.core.port.UserRepository;
import com.example.core.utils.Constants;
import com.example.core.utils.FieldsUtils;
import com.sun.jdi.request.DuplicateRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest extends FactoryBase {
    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;
    @Mock
    private UserRepository repository;
    @Mock
    private FieldsUtils fieldsUtils;

    private User user;

    @BeforeEach
    public void setUp() {
        user = getUser();
    }

    @Test
    @DisplayName("Deve criar usuário com sucesso")
    public void shouldCreateUserSuccessfully() {
        when(repository.save(getUser())).thenReturn(getUser());

        doNothing().when(fieldsUtils).validateDuplicity(anyString(), anyString());
        var user = createUserUseCase.create(getUser());

        assertEquals(getUser(), user);
        verify(repository, times(1)).save(getUser());
    }

    @Test
    @DisplayName("Deve lançar exceção quando o nome é nulo")
    public void shouldThrowExceptionWhenNameIsNull() {
        user.setName(null);
        assertThatThrownBy(() -> createUserUseCase.create(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.NAME_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o CPF é nulo")
    public void shouldThrowExceptionWhenCPFIsNull() {
        user.setCpf(null);
        assertThatThrownBy(() -> createUserUseCase.create(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.CPF_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o e-mail é nulo")
    public void shouldThrowExceptionWhenEmailIsNull() {
        user.setEmail(null);
        assertThatThrownBy(() -> createUserUseCase.create(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.EMAIL_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o CPF estiver duplicado")
    public void shouldThrowExceptionWhenCpfIsDuplicated() {
        doThrow(new DuplicateRequestException(Constants.DUPLICATION_CPF))
                .when(fieldsUtils).validateDuplicity(eq(user.getCpf()), eq(Constants.DUPLICATION_CPF));

        assertThatThrownBy(() -> createUserUseCase.create(user))
                .isInstanceOf(DuplicateRequestException.class)
                .hasMessage(Constants.DUPLICATION_CPF);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o e-mail estiver duplicado")
    public void shouldThrowExceptionWhenEmailIsDuplicated() {

        doNothing().when(fieldsUtils).validateDuplicity(eq(user.getCpf()), anyString());

        doThrow(new DuplicateRequestException(Constants.DUPLICATION_EMAIL))
                .when(fieldsUtils).validateDuplicity(eq(user.getEmail()), eq(Constants.DUPLICATION_EMAIL));

        assertThatThrownBy(() -> createUserUseCase.create(user))
                .isInstanceOf(DuplicateRequestException.class)
                .hasMessage(Constants.DUPLICATION_EMAIL);
    }
}