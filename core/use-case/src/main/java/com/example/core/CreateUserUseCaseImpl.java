package com.example.core;

import com.example.core.port.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;

    @Inject
    public CreateUserUseCaseImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(final User user) {
        user.validate();
        return userRepository.save(user);
    }
}
