package br.com.example.entrypoints.dto.request;

import br.com.example.entrypoints.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@JsonIgnoreProperties("id")
@EqualsAndHashCode(callSuper = true)
public class UserRequest extends UserDTO {
}
