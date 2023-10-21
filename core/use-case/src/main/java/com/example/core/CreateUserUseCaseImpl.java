package com.example.core;

import com.example.core.port.UserRepository;
import com.example.core.utils.Constants;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import static com.example.core.exception.ExceptionUtil.throwExceptionIf;

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

        throwExceptionIf(userRepository.findByFilter(user.getCpf()).stream().findFirst().isPresent(),
                new DuplicateRequestException(Constants.DUPLICATION_CPF));

        throwExceptionIf(userRepository.findByFilter(user.getEmail()).stream().findFirst().isPresent(),
                new DuplicateRequestException(Constants.DUPLICATION_EMAIL));

        return userRepository.save(user);
    }
}
