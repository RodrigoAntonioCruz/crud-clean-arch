package com.example.adapter.input.controller.mapper;


import com.example.adapter.input.controller.dto.request.UserRequest;
import com.example.adapter.input.controller.dto.response.UserResponse;
import com.example.core.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserInputMapper {
    UserInputMapper INSTANCE = Mappers.getMapper(UserInputMapper.class);
    UserResponse toResponse(User user);
    User toEntity(UserRequest userRequest);
}

