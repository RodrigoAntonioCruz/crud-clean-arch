package com.example.core;

import com.example.core.exception.UserNotFoundException;
import com.example.core.port.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class FindByIdUserUseCaseImpl implements FindByIdUserUseCase {
    private final UserRepository userRepository;

    @Inject
    public FindByIdUserUseCaseImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(String id) {
        return userRepository.findUserById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
