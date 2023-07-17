package br.com.example.entrypoints.dto.response;

import br.com.example.entrypoints.dto.UserDTO;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UserResponse extends UserDTO {
    public UserResponse(String id, String name, String cpf, String email) {
        super(id, name, cpf, email);
    }
}