package com.example.core;


import com.example.core.factory.FactoryBase;
import com.example.core.port.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class FindByIdUserUseCaseTest extends FactoryBase {

    @InjectMocks
    private FindByIdUserUseCaseImpl findByIdUserUseCase;

    @Mock
    private UserRepository repository;

    @BeforeEach
    public void setUp() {

    }
    @Test
    @DisplayName("Deve construir o usuário")
    public void shouldBuildUser() {

    }
}
