package com.example.core;

import java.util.List;

public interface FindByFilterUserUseCase {

    List<User> findByFilter(String query);
}
