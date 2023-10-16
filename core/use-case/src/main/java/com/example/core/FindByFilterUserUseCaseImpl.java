package com.example.core;

import com.example.core.port.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@ApplicationScoped
public class FindByFilterUserUseCaseImpl implements FindByFilterUserUseCase {
    private final UserRepository userRepository;

    @Inject
    public FindByFilterUserUseCaseImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findByFilter(String query) {
        return userRepository.findByFilter(query);
    }

}
