package com.example.adapter.input.controller.factory;

import com.example.adapter.input.controller.dto.request.UserRequest;
import com.example.adapter.input.controller.dto.response.UserResponse;
import com.example.core.User;

import java.util.List;

public abstract class FactoryBase {
    public static final String USER_ID = "6488760121467b0d0b2e53de";
    public static final String USER_NAME = "João Ribeiro da Silva Teixeira";
    public static final String USER_CPF = "82167705026";
    public static final String USER_EMAIL = "joaoribeiro@msn.com";
    public static final String FILTER = "joa";
    public static final Integer PAGE = 0;
    public static final Integer LINES = 100;
    public static final String DIRECTION = "ASC";
    public static final String ORDER_BY = "id";
    public static UserRequest getUserRequest() {
        return UserRequest.builder()
                .name(USER_NAME)
                .cpf(USER_CPF)
                .email(USER_EMAIL)
                .build();
    }
    public static UserResponse getUserResponse() {
        return UserResponse.builder()
                .id(USER_ID)
                .name(USER_NAME)
                .cpf(USER_CPF)
                .email(USER_EMAIL)
                .build();
    }
    public static User getUser() {
        return new User(USER_ID, USER_NAME, USER_CPF, USER_EMAIL);
    }
    public static List<UserResponse> getListUserResponse() {
        return List.of(getUserResponse());
    }
}