package com.example.adapter.input.controller;


import com.example.adapter.input.controller.dto.request.UserRequest;
import com.example.adapter.input.controller.dto.response.UserResponse;
import com.example.adapter.input.controller.mapper.UserInputMapper;
import com.example.adapter.input.controller.utils.PageUtils;
import com.example.core.CreateUserUseCase;
import com.example.core.DeleteByIdUserUseCase;
import com.example.core.FindByFilterUserUseCase;
import com.example.core.FindByIdUserUseCaseImpl;
import com.example.core.UpdateUserUseCase;
import com.example.core.User;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "Usuários")
@RequestMapping("/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final FindByIdUserUseCaseImpl findByIdUserUseCase;
    private final FindByFilterUserUseCase findByFilterUserUseCase;
    private final DeleteByIdUserUseCase deleteByIdUserUseCase;

    @PostMapping
    @Operation(description = "Cria um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "409", description = "Duplicidade nos dados informados"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userRequest) {
        User user = UserInputMapper.INSTANCE.toEntity(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserInputMapper.INSTANCE.toResponse(createUserUseCase.create(user)));
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
    public ResponseEntity<UserResponse> update(@PathVariable String id, @Valid @RequestBody UserRequest userRequest) {
        User user = UserInputMapper.INSTANCE.toEntity(userRequest);
        return ResponseEntity.ok(UserInputMapper.INSTANCE.toResponse(updateUserUseCase.update(id, user)));
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
        return ResponseEntity.ok(UserInputMapper.INSTANCE.toResponse(findByIdUserUseCase.findById(id)));
    }

    @GetMapping
    @Operation(description = "Busca paginada de usuários por filtros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados."),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<Page<UserResponse>> findAll(@Valid
                                                      @RequestParam(value = "query", defaultValue = "") String query,
                                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "linesPerPage", defaultValue = "100") Integer linesPerPage,
                                                      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                      @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        List<User> list = findByFilterUserUseCase.findByFilter(query);
        Pageable pageable = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<UserResponse> pages = PageUtils.toPage(list, pageable, list.size(), UserInputMapper.INSTANCE::toResponse, orderBy, UserResponse.class);
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
        deleteByIdUserUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
