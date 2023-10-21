package com.example.adapter.output.repository;


import com.example.adapter.output.repository.factory.FactoryBase;
import com.example.adapter.output.repository.mapper.UserOutputMapper;
import com.example.core.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest extends FactoryBase {
    @InjectMocks
    private UserRepositoryImpl repository;
    @Mock
    private UserEntityRepository userEntityRepository;
    @Spy
    private UserOutputMapper mapper;

    @BeforeEach
    public void setUp() {
        when(mapper.toUserEntity(getUser())).thenReturn(getUserEntity());

        when(mapper.toDomain(getUserEntity())).thenReturn(getUser());
    }

    @Test
    @DisplayName("Deve retornar o usuário ao salvar um usuário")
    public void shouldReturnUserWhenSavedUser() {
        saveUserRepository();

        responseUser(repository.save(getUser()));

        verify(userEntityRepository, times(1)).save(getUserEntity());
    }

    @Test
    @DisplayName("Deve retornar o usuário ao encontrar um usuário por ID")
    public void shouldReturnUserWhenFindingUserById() {
        findUserByIdRepository();
        Optional<User> userOptional = repository.findUserById(USER_ID);

        assertTrue(userOptional.isPresent());
        responseUser(userOptional.get());

        verify(userEntityRepository, times(1)).findById(USER_ID);
    }

    @Test
    @DisplayName("Deve retornar uma lista de usuários ao encontrar usuários por filtros")
    public void shouldReturnListUserWhenFindByFiltersUsers() {
        findByFiltersUserRepository();

        responseUser(repository.findByFilter(FILTER).get(0));

        verify(userEntityRepository, times(1)).findByFilter(FILTER);
    }

    @Test
    @DisplayName("Não deve retornar nada ao excluir um usuário")
    public void shouldNotReturnNothingWhenDeletingUser() {
        deleteByIdUserRepository();

        repository.deleteById(USER_ID);

        verify(userEntityRepository, times(1)).deleteById(USER_ID);
    }

    private void saveUserRepository() {
        when(userEntityRepository.save(getUserEntity())).thenReturn(getUserEntity());
    }

    private void findUserByIdRepository() {
        lenient().when(mapper.toUserEntity(getUser())).thenReturn(null);
        when(userEntityRepository.findById(USER_ID)).thenReturn(Optional.of(getUserEntity()));
    }

    private void findByFiltersUserRepository() {
        lenient().when(mapper.toUserEntity(getUser())).thenReturn(null);
        when(userEntityRepository.findByFilter(FILTER)).thenReturn(List.of(getUserEntity()));
    }

    private void deleteByIdUserRepository() {
        lenient().when(mapper.toUserEntity(getUser())).thenReturn(null);
        lenient().when(mapper.toDomain(getUserEntity())).thenReturn(null);
        doNothing().when(userEntityRepository).deleteById(USER_ID);
    }

    private void responseUser(User response) {
        assertThat(response.getId()).isEqualTo(getUser().getId());
        assertThat(response.getName()).isEqualTo(getUser().getName());
        assertThat(response.getCpf()).isEqualTo(getUser().getCpf());
        assertThat(response.getEmail()).isEqualTo(getUser().getEmail());
    }
}