package com.example.core.utils;

import com.example.core.port.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class FieldsUtils {
    private final UserRepository userRepository;

    @Inject
    public FieldsUtils(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateDuplicity(String value, String message) {
        if (userRepository.findByFilter(value).stream().findFirst().isPresent()) {
            throw new DuplicateRequestException(message);
        }
    }
}