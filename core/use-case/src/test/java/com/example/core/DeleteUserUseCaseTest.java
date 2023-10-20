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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class DeleteUserUseCaseTest extends FactoryBase {

    @InjectMocks
    private DeleteByIdUserUseCaseImpl deleteUserUseCase;

    @Mock
    private UserRepository repository;

    @Mock
    private FindByIdUserUseCaseImpl findByIdUserUseCase;

    private User user;

    @BeforeEach
    public void setUp() {
        user = getUser();
    }

    @Test
    @DisplayName("Deve excluir usuário por ID com sucesso")
    public void shouldDeleteUserByIdSuccessfully() {
        when(findByIdUserUseCase.findById(USER_ID)).thenReturn(user);

        assertDoesNotThrow(() -> deleteUserUseCase.deleteById(USER_ID));

        verify(repository, times(1)).deleteById(USER_ID);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar exclusão de usuário inexistente")
    public void shouldThrowExceptionWhenTryingDeleteNonExistentUser() {
        doThrow(new UserNotFoundException())
                .when(findByIdUserUseCase).findById(INVALID_USER_ID);

        assertThatThrownBy(() -> deleteUserUseCase.deleteById(INVALID_USER_ID))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage(Constants.USER_NOT_FOUND);

        verify(repository, never()).deleteById(INVALID_USER_ID);
    }
}