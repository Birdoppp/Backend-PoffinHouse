package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.output.AuthorityOutputDto;
import com.novi.poffinhouse.util.RoleEnum;
import com.novi.poffinhouse.services.AuthorityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/authorities")
public class AuthorityController {

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @PostMapping("/assign/{username}/{role}")
    public ResponseEntity<AuthorityOutputDto> assignRole(
            @PathVariable String username,
            @PathVariable RoleEnum role) {

        AuthorityOutputDto createdAuthority = authorityService.assignAuthority(username, role);
        return new ResponseEntity<>(createdAuthority, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getAuthoritiesByUsername(@PathVariable String username) {
        try {
            Set<AuthorityOutputDto> authorities = authorityService.getAuthoritiesByUsername(username);
            return authorities.isEmpty() ? ResponseEntity.ok(username +": has no authorities.") : ResponseEntity.ok(authorities);
        } catch (RuntimeException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }


    @DeleteMapping("/removeAll/{username}")
    public ResponseEntity<String> removeAllAuthorities(@PathVariable String username) {
        String message = authorityService.removeAllAuthorities(username);
        return ResponseEntity.ok(message);
    }
}
