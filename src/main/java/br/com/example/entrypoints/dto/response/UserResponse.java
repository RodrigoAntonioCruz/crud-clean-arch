package br.com.example.entrypoints.dto.response;

import br.com.example.entrypoints.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends UserDTO {
}
