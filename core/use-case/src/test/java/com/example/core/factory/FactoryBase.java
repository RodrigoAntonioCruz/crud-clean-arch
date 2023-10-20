package com.example.core.factory;

import com.example.core.User;

public abstract class FactoryBase {
    public static final String USER_ID = "6488760121467b0d0b2e53de";
    public static final String INVALID_USER_ID = "5760121467b0d0b2e53de";
    public static final String USER_NAME = "João Ribeiro da Silva Teixeira";
    public static final String USER_CPF = "19748004074";
    public static final String USER_EMAIL = "joaoribeiro@msn.com";
    public static final String FILTER = "Joã";
    public static User getUser() {
        return new User(USER_ID, USER_NAME, USER_CPF, USER_EMAIL);
    }
}