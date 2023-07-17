package br.com.example.core.dataprovider;


import br.com.example.core.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
    User save(User user);
    Optional<User> findUserById(String id);
    List<User> findBySearch(String query);
    void deleteById(String id);
}
