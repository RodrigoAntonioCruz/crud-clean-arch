package com.example.adapter.output.repository.mapper;


import com.example.adapter.output.repository.entity.UserEntity;
import com.example.core.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserOutputMapper {
    UserOutputMapper INSTANCE = Mappers.getMapper(UserOutputMapper.class);
    User toDomain(UserEntity userEntity);
    UserEntity toUserData(User user);
}

