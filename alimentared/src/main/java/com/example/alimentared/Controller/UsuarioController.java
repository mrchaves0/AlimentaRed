package com.example.alimentared.Controller;

import com.example.alimentared.Dto.Request.RegistroUsuarioRequestDto;
import com.example.alimentared.Dto.Response.UsuarioResponseDto;
import com.example.alimentared.Service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDto> registrar(@Valid @RequestBody RegistroUsuarioRequestDto dto) {
        UsuarioResponseDto nuevoUsuario = usuarioService.registrarUsuario(dto);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}