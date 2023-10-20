package com.example.core;

import com.example.core.port.UserRepository;
import com.example.core.utils.Constants;
import com.example.core.utils.FieldsUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final FieldsUtils fieldsUtils;
    private final UserRepository userRepository;

    @Inject
    public CreateUserUseCaseImpl(FieldsUtils fieldsUtils, final UserRepository userRepository) {
        this.fieldsUtils = fieldsUtils;
        this.userRepository = userRepository;
    }

    @Override
    public User create(final User user) {
        user.validate();
        fieldsUtils.validateDuplicity(user.getCpf(), Constants.DUPLICATION_CPF);
        fieldsUtils.validateDuplicity(user.getEmail(), Constants.DUPLICATION_EMAIL);
        return userRepository.save(user);
    }
}
