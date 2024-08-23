package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.UserInputDto;
import com.novi.poffinhouse.dto.output.UserOutputDto;
import com.novi.poffinhouse.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserOutputDto> createUser(@Valid @RequestBody UserInputDto userInputDto) {
        UserOutputDto userDTO = userService.createUser(userInputDto);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
    // Get by Id, Email or Username
    @GetMapping("/{field}/{value}")
    public ResponseEntity<UserOutputDto> getUserByField(@PathVariable String field, @PathVariable String value) {
        UserOutputDto userDTO = userService.getUserBy(field, value);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserOutputDto> updateUser(@PathVariable Long id,@RequestBody UserInputDto userInputDto) {
        UserOutputDto updatedUser = userService.updateUser(id, userInputDto);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@Valid @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
