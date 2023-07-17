package br.com.example.core.usecase;

import br.com.example.core.entity.User;

import java.util.List;

public interface UserUseCase {

    User create(User user);

    User update(String id, User user);

    User findById(String id);

    List<User> findBySearch(String query);

    void deleteById(String id);
}
