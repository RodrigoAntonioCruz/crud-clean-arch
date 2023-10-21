package com.example.core;


import com.example.core.exception.UserNotFoundException;
import com.example.core.factory.FactoryBase;
import com.example.core.port.UserRepository;
import com.example.core.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindByIdUserUseCaseTest extends FactoryBase {

    @InjectMocks
    private FindByIdUserUseCaseImpl findByIdUserUseCase;

    @Mock
    private UserRepository repository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = getUser();
    }
    @Test
    @DisplayName("Deve encontrar usuário por ID")
    public void shouldFindUserById() {
        when(repository.findUserById(USER_ID)).thenReturn(Optional.ofNullable(user));
        var response = findByIdUserUseCase.findById(USER_ID);

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(user.getName());
    }

    @Test
    @DisplayName("Deve Lançar Exceção ao Buscar Usuário Inexistente")
    public void shouldThrowExceptionWhenSearchingForNonExistingUser() {
        doThrow(new UserNotFoundException())
                .when(repository).findUserById(INVALID_USER_ID);

        assertThatThrownBy(() -> findByIdUserUseCase.findById(INVALID_USER_ID))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage(Constants.USER_NOT_FOUND);
    }
}

