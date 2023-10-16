package com.example.core;

import com.example.core.port.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class DeleteByIdDeleteByIdUserUseCaseImpl implements DeleteByIdUserUseCase {
    private final UserRepository userRepository;
    private final FindByIdUserUseCaseImpl findByIdUserUseCase;

    @Inject
    public DeleteByIdDeleteByIdUserUseCaseImpl(FindByIdUserUseCaseImpl findByIdUserUseCase, final UserRepository userRepository) {
        this.findByIdUserUseCase = findByIdUserUseCase;
        this.userRepository = userRepository;
    }

     @Override
    public void deleteById(String id) {
        User user = findByIdUserUseCase.findById(id);
        userRepository.deleteById(user.getId());
    }
}
