package com.example.adapter.output.repository.factory;

import com.example.adapter.output.repository.entity.UserEntity;
import com.example.core.User;

public abstract class FactoryBase {
    public static final String USER_ID = "6488760121467b0d0b2e53de";
    public static final String USER_NAME = "João Ribeiro da Silva Teixeira";
    public static final String USER_CPF = "82167705026";
    public static final String USER_EMAIL = "joaoribeiro@msn.com";
    public static final String FILTER = "joa";
    public static UserEntity getUserEntity() {
        return UserEntity.builder()
                .id(USER_ID)
                .name(USER_NAME)
                .cpf(USER_CPF)
                .email(USER_EMAIL)
                .build();
    }
    public static User getUser() {
        return new User(USER_ID, USER_NAME, USER_CPF, USER_EMAIL);
    }
}