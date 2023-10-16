package com.example.core.port;




import com.example.core.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findUserById(String id);
    List<User> findByFilter(String query);
    void deleteById(String id);
}
