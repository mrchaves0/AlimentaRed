package com.example.alimentared.Controller;

import com.example.alimentared.Dto.Request.LoginRequestDto;
import com.example.alimentared.Dto.Response.UsuarioResponseDto;
import com.example.alimentared.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDto> login(@Valid @RequestBody LoginRequestDto dto) {
        return new ResponseEntity<>(authService.login(dto), HttpStatus.OK);
    }
}