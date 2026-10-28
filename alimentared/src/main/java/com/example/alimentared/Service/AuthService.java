package com.example.alimentared.Service;

import com.example.alimentared.Dto.Request.LoginRequestDto;
import com.example.alimentared.Dto.Response.UsuarioResponseDto;
import com.example.alimentared.Entity.Usuario;
import com.example.alimentared.Repository.UsuarioRepository;
import com.example.alimentared.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UsuarioResponseDto login(LoginRequestDto dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Correo o contraseña incorrectos"));

        if (!passwordEncoder.matches(dto.getPassword(), usuario.getPasswordHash())) {
            throw new RuntimeException("Correo o contraseña incorrectos");
        }

        String token = jwtService.generateToken(usuario);

        UsuarioResponseDto response = new UsuarioResponseDto();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setEmail(usuario.getEmail());
        response.setRol(usuario.getRol());
        response.setActivo(usuario.getActivo());
        response.setCreadoEn(usuario.getCreadoEn());
        response.setToken(token);

        return response;
    }
}
