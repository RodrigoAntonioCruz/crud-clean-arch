package com.example.core;

import com.example.core.port.UserRepository;
import com.example.core.utils.Constants;
import com.example.core.utils.FieldsUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final FieldsUtils fieldsUtils;
    private final UserRepository userRepository;
    private final FindByIdUserUseCaseImpl findByIdUserUseCase;

    @Inject
    public UpdateUserUseCaseImpl(FieldsUtils fieldsUtils, final UserRepository userRepository, FindByIdUserUseCaseImpl findByIdUserUseCase) {
        this.fieldsUtils = fieldsUtils;
        this.userRepository = userRepository;
        this.findByIdUserUseCase = findByIdUserUseCase;
    }

    @Override
    public User update(String id, User user) {
        User existingUser = findByIdUserUseCase.findById(id);

        user.validate();
        fieldsUtils.validateDuplicity(user.getCpf(), Constants.CPF_INVALID);
        fieldsUtils.validateDuplicity(user.getEmail(), Constants.EMAIL_INVALID);

        existingUser.setId(id);
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());

        return userRepository.save(existingUser);
    }
}
