package com.example.adapter.input.controller;

import com.example.adapter.input.controller.factory.FactoryBase;
import com.example.adapter.input.controller.mapper.UserInputMapper;
import com.example.core.CreateUserUseCase;
import com.example.core.DeleteByIdUserUseCase;
import com.example.core.FindByFilterUserUseCase;
import com.example.core.FindByIdUserUseCaseImpl;
import com.example.core.UpdateUserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest extends FactoryBase {

    @InjectMocks
    private UserController userController;
    @Mock
    private CreateUserUseCase createUserUseCase;
    @Mock
    private UpdateUserUseCase updateUserUseCase;

    @Mock
    private FindByIdUserUseCaseImpl findByIdUserUseCase;

    @Mock
    private FindByFilterUserUseCase findByFilterUserUseCase;

    @Mock
    private DeleteByIdUserUseCase deleteByIdUserUseCase;
    @Spy
    private UserInputMapper mapper;

    @BeforeEach
    public void setUp() {
        when(mapper.toEntity(getUserRequest())).thenReturn(getUser());

        when(mapper.toResponse(getUser())).thenReturn(getUserResponse());
    }

    @Test
    @DisplayName("Deve retornar o usuário ao criar um usuário")
    public void shouldReturnUserWhenCreatingUser() {
        createUserUseCase();

        var response = userController.create(getUserRequest());

        assertThat(response.getBody()).isEqualTo(getUserResponse());
    }

    @Test
    @DisplayName("Deve retornar o código de status ao criar um usuário")
    public void shouldReturnStatusCodeWhenCreatingUser() {
        createUserUseCase();

        var response = userController.create(getUserRequest());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("Deve retornar o usuário ao atualizar um usuário")
    public void shouldReturnUserWhenUpdatingUser() {
        updateUserUseCase();

        var response = userController.update(USER_ID, getUserRequest());

        assertThat(response.getBody()).isEqualTo(getUserResponse());
    }

    @Test
    @DisplayName("Deve retornar o código de status ao atualizar um usuário")
    public void shouldReturnStatusCodeWhenUpdatingUser() {
        updateUserUseCase();

        var response = userController.update(USER_ID, getUserRequest());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Deve retornar o usuário ao encontrar um usuário por ID")
    public void shouldReturnUserWhenFindingUserById() {
        findByIdUserUseCase();

        var response = userController.findById(USER_ID);

        assertThat(response.getBody()).isEqualTo(getUserResponse());
    }

    @Test
    @DisplayName("Deve retornar o código de status ao encontrar um usuário por ID")
    public void shouldReturnStatusCodeWhenFindingUserById() {
        findByIdUserUseCase();

        var response = userController.findById(USER_ID);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Deve retornar uma lista paginada de usuários ao encontrar usuários por filtros")
    public void shouldReturnListUserWhenFindByFiltersUsers() {
        findByFiltersUserUseCase();

        var response = userController.findByFilter(FILTER, PAGE, LINES, DIRECTION, ORDER_BY);

        assertThat(Objects.requireNonNull(response.getBody()).getContent()).isEqualTo(getListUserResponse());
    }

    @Test
    @DisplayName("Deve retornar o código de status ao encontrar todos os usuários")
    public void shouldReturnStatusCodeWhenFindByFiltersUsers() {
        findByFiltersUserUseCase();

        var response = userController.findByFilter(FILTER, PAGE, LINES, DIRECTION, ORDER_BY);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Não deve retornar nada ao excluir um usuário")
    public void shouldNotReturnNothingWhenDeletingUser() {
        deleteByIdUserUseCase();

        var response = userController.deleteById(USER_ID);

        assertThat(response.getBody()).isNull();
    }

    @Test
    @DisplayName("Deve retornar o código de status ao excluir um usuário")
    public void shouldReturnStatusCodeWhenDeletingUser() {
        deleteByIdUserUseCase();

        var response = userController.deleteById(USER_ID);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    private void createUserUseCase() {
        when(createUserUseCase.create(getUser())).thenReturn(getUser());
    }

    private void updateUserUseCase() {
        when(updateUserUseCase.update(USER_ID, getUser())).thenReturn(getUser());
    }
    private void findByIdUserUseCase() {
        lenient().when(mapper.toEntity(getUserRequest())).thenReturn(null);
        when(findByIdUserUseCase.findById(USER_ID)).thenReturn(getUser());
    }

    private void findByFiltersUserUseCase() {
        lenient().when(mapper.toEntity(getUserRequest())).thenReturn(null);
        when(findByFilterUserUseCase.findByFilter(FILTER)).thenReturn(List.of(getUser()));
    }

    private void deleteByIdUserUseCase() {
        lenient().when(mapper.toEntity(getUserRequest())).thenReturn(null);
        lenient().when(mapper.toResponse(getUser())).thenReturn(null);
        doNothing().when(deleteByIdUserUseCase).deleteById(USER_ID);
    }
}