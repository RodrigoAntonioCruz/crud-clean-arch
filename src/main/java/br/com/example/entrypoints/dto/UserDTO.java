package br.com.example.entrypoints.dto;

import br.com.example.utils.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserDTO {

    @Schema(defaultValue = "Id do usuário", example = "64889fb6f3a692337867ea64")
    private String id;

    @NotBlank(message = Constants.NAME_NOT_NULL)
    @Length(min=3, max=120, message = Constants.NAME_MAX_LENGTH)
    @Schema(defaultValue = "Nome do usuário", example = "Pedro da Costa")
    private String name;

    @CPF(message = Constants.CPF_INVALID)
    @NotBlank(message = Constants.CPF_NOT_NULL)
    @Length(max=11, message = Constants.CPF_MAX_LENGTH)
    @Schema(defaultValue = "CPF do usuário", example = "85907544058")
    private String cpf;

    @Email(message = Constants.EMAIL_INVALID)
    @NotBlank(message = Constants.EMAIL_NOT_NULL)
    @Length(max=120, message = Constants.EMAIL_MAX_LENGTH)
    @Schema(defaultValue = "E-mail do usuário", example = "pedrocosta@gmail.com")
    private String email;

}
