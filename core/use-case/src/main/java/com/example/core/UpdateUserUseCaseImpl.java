package com.example.core;

import com.example.core.port.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserRepository userRepository;
    private final FindByIdUserUseCaseImpl findByIdUserUseCase;

    @Inject
    public UpdateUserUseCaseImpl(final UserRepository userRepository, FindByIdUserUseCaseImpl findByIdUserUseCase) {
        this.userRepository = userRepository;
        this.findByIdUserUseCase = findByIdUserUseCase;
    }

    @Override
    public User update(String id, User user) {
        User existingUser = findByIdUserUseCase.findById(id);

        existingUser.setId(id);
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());

        return userRepository.save(existingUser);
    }
}
