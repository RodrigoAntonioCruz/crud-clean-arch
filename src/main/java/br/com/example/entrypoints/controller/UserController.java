package br.com.example.entrypoints.controller;

import br.com.example.core.entity.User;
import br.com.example.core.usecase.UserUseCase;
import br.com.example.entrypoints.dto.request.UserRequest;
import br.com.example.entrypoints.dto.response.UserResponse;
import br.com.example.entrypoints.mapper.UserMapper;
import br.com.example.utils.PageUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "Usuários")
@RequestMapping("/users")
public class UserController {
    private final UserUseCase userUseCase;

    @PostMapping
    @Operation(description = "Cria um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "409", description = "Duplicidade nos dados informados"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userRequest) {
        User user = UserMapper.INSTANCE.toEntity(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.INSTANCE.toResponse(userUseCase.create(user)));
    }

    @PutMapping("/{id}")
    @Operation(description = "Atualiza um usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado"),
            @ApiResponse(responseCode = "409", description = "Duplicidade nos dados informados"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<UserResponse> update(@Valid @PathVariable String id, @RequestBody UserRequest userRequest) {
        User user = UserMapper.INSTANCE.toEntity(userRequest);
        return ResponseEntity.ok(UserMapper.INSTANCE.toResponse(userUseCase.update(id, user)));
    }

    @GetMapping("/{id}")
    @Operation(description = "Busca um usuário por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<UserResponse> findById(@Valid @PathVariable String id) {
        return ResponseEntity.ok(UserMapper.INSTANCE.toResponse(userUseCase.findById(id)));
    }

    @GetMapping
    @Operation(description = "Busca paginada de usuários por filtros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados."),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<Page<UserResponse>> findAll(@Valid @RequestParam(value = "query", defaultValue = "") String query,
                                                 @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "linesPerPage", defaultValue = "100") Integer linesPerPage,
                                                 @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                 @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        List<User> list = userUseCase.findBySearch(query);
        Pageable pageable = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<UserResponse> pages = PageUtils.toPage(list, pageable, list.size(), UserMapper.INSTANCE::toResponse, orderBy, UserResponse.class);
        return ResponseEntity.ok().body(pages);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Remove um usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Solicitação sem conteúdo realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados."),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<Void> deleteById(@Valid @PathVariable String id) {
        userUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
