package br.com.example.entrypoints.controller;

import br.com.example.core.domain.User;
import br.com.example.core.usecase.UserUseCase;
import br.com.example.entrypoints.dto.UserDTO;
import br.com.example.entrypoints.mapper.UserMapper;
import br.com.example.utils.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<UserDTO>> findAll(@RequestParam(value = "query", defaultValue = "") String query,
                                                 @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "linesPerPage", defaultValue = "100") Integer linesPerPage,
                                                 @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                 @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {

        List<User> list = userUseCase.findBySearch(query);

        Pageable pageable = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<UserDTO> pageUsers = PageUtils.toPage(list, pageable, list.size(), UserMapper.INSTANCE::toDTO, orderBy, UserDTO.class);

        return ResponseEntity.ok().body(pageUsers);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        userUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
