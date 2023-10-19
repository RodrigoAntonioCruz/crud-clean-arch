package com.example.core;


import com.example.core.factory.FactoryBase;
import com.example.core.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDomainTest extends FactoryBase {
    private User user;

    @BeforeEach
    public void setUp() {
        user = getUser();
    }

    @Test
    @DisplayName("Deve construir um usuário com sucesso")
    public void shouldBuildUserSuccessfully() {
        assertEquals(user.getId(), USER_ID);
        assertEquals(user.getName(), USER_NAME);
        assertEquals(user.getEmail(), USER_EMAIL);
        assertEquals(user.getCpf(), USER_CPF);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o nome é nulo")
    public void shouldThrowExceptionWhenNameIsNull() {
        user.setName(null);
        validateException(Constants.NAME_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o CPF é nulo")
    public void shouldThrowExceptionWhenCPFIsNull() {
        user.setCpf(null);
        validateException(Constants.CPF_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o e-mail é nulo")
    public void shouldThrowExceptionWhenEmailIsNull() {
        user.setEmail(null);
        validateException(Constants.EMAIL_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o nome é vazio")
    public void shouldThrowExceptionWhenNameIsEmpty() {
        user.setName("");
        validateException(Constants.NAME_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o CPF é vazio")
    public void shouldThrowExceptionWhenCPFIsEmpty() {
        user.setCpf("");
        validateException(Constants.CPF_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o e-mail é vazio")
    public void shouldThrowExceptionWhenEmailIsEmpty() {
        user.setEmail("");
        validateException(Constants.EMAIL_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o e-mail é inválido")
    public void shouldThrowExceptionWhenEmailIsInvalid() {
        user.setEmail("email_invalid.com");
        validateException(Constants.EMAIL_INVALID);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o CPF é inválido")
    public void shouldThrowExceptionWhenCPFIsInvalid() {
        user.setCpf("000448723432");
        validateException(Constants.CPF_INVALID);
    }

    private void validateException(String message) {
        assertThatThrownBy(() -> user.validate())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }
}
