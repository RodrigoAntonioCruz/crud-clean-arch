package br.com.example.entrypoints.mapper;

import br.com.example.core.domain.User;
import br.com.example.entrypoints.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

}