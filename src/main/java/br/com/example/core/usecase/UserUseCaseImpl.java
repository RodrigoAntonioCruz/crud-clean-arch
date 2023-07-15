package br.com.example.core.usecase;

import br.com.example.core.dataprovider.UserRepository;
import br.com.example.core.domain.User;

import java.util.List;

public class UserUseCaseImpl implements UserUseCase {

    private final UserRepository userRepository;

    public UserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(String id, User user) {
        User existingUser = findUserById(id);

        existingUser.setId(id);
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());

        return userRepository.save(existingUser);
    }

    @Override
    public User findById(String id) {
        return  findUserById(id);
    }

    @Override
    public List<User> findBySearch(String query) {
        return userRepository.findBySearch(query);
    }

    @Override
    public void deleteById(String id) {
        User user = findUserById(id);
        userRepository.deleteById(user.getId());
    }

    private User findUserById(String id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return  user;
    }
}
