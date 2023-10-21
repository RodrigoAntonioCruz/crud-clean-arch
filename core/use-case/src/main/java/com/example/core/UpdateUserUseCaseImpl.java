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

        user.validate();

        throwExceptionIf(userRepository.findByFilter(user.getCpf()).stream().findFirst().isPresent(),
                new DuplicateRequestException(Constants.DUPLICATION_CPF));

        throwExceptionIf(userRepository.findByFilter(user.getEmail()).stream().findFirst().isPresent(),
                new DuplicateRequestException(Constants.DUPLICATION_EMAIL));

        existingUser.setId(id);
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());

        return userRepository.save(existingUser);
    }
}
