package br.com.example.entrypoint.controller;

import br.com.example.core.domain.User;
import br.com.example.core.usecase.UserUseCase;
import br.com.example.entrypoint.dto.UserDTO;
import br.com.example.entrypoint.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserUseCase userUseCase;

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        User user = UserMapper.INSTANCE.toEntity(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.INSTANCE.toDTO(userUseCase.create(user)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO userDTO) {
        User user = UserMapper.INSTANCE.toEntity(userDTO);
        return ResponseEntity.ok(UserMapper.INSTANCE.toDTO(userUseCase.update(id, user)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(UserMapper.INSTANCE.toDTO(userUseCase.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(UserMapper.INSTANCE.toDTOList(userUseCase.findAll()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        userUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
