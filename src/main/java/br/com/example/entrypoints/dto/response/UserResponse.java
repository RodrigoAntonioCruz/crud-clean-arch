package br.com.example.entrypoints.dto.response;

import br.com.example.entrypoints.dto.UserDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends UserDTO {
}
