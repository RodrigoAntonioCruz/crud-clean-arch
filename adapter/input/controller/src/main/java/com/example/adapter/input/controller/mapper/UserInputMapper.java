package com.example.adapter.input.controller.mapper;


import com.example.adapter.input.controller.dto.request.UserRequest;
import com.example.adapter.input.controller.dto.response.UserResponse;
import com.example.core.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserInputMapper {
    UserResponse toResponse(User user);
    User toEntity(UserRequest userRequest);
}

