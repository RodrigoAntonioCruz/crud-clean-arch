package br.com.example.core.dataprovider;


import br.com.example.core.domain.User;

import java.util.List;


public interface UserRepository {
    User save(User user);
    User findUserById(String id);
    List<User> findBySearch(String query);
    void deleteById(String id);
}
