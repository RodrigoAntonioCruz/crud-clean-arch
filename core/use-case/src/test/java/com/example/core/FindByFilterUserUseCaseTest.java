package com.example.core;


import com.example.core.factory.FactoryBase;
import com.example.core.port.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class FindByFilterUserUseCaseTest extends FactoryBase {

    @InjectMocks
    private FindByFilterUserUseCaseImpl findByFilterUserUseCase;

    @Mock
    private UserRepository repository;

    private User user;

    @BeforeEach
    public void setUp() {
      user = getUser();
    }
    @Test
    @DisplayName("Deve encontrar usuários por filtro")
    public void shouldFindUsersByFilter() {

        when(repository.findByFilter(FILTER)).thenReturn(List.of(user));

        var result = findByFilterUserUseCase.findByFilter(FILTER);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName().substring(0, 3)).isEqualTo(FILTER);
    }
}
