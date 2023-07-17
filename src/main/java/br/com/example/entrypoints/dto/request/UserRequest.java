package br.com.example.entrypoints.dto.request;

import br.com.example.entrypoints.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties("id")
@EqualsAndHashCode(callSuper = true)
public class UserRequest extends UserDTO {
    public UserRequest(String id, String name, String cpf, String email) {
        super(id, name, cpf, email);
    }
}
