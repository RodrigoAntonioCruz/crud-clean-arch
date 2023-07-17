package br.com.example.entrypoints.mapper;

import br.com.example.core.entity.User;
import br.com.example.dataproviders.data.UserData;
import br.com.example.entrypoints.dto.request.UserRequest;
import br.com.example.entrypoints.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserRequest toRequest(User user);

    UserResponse toResponse(User user);

    User toEntity(UserRequest userRequest);

    User toDomain(UserData userData);

    UserData toUserData(User user);
}

