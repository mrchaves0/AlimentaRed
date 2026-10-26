package com.example.alimentared.Service;

import com.example.alimentared.Dto.Request.RegistroUsuarioRequestDto;
import com.example.alimentared.Dto.Response.UsuarioResponseDto;
import com.example.alimentared.Entity.Usuario;
import com.example.alimentared.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioResponseDto registrarUsuario(RegistroUsuarioRequestDto dto) {

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario registrado con este correo");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());


        usuario.setPasswordHash(passwordEncoder.encode(dto.getPassword()));

        usuario.setRol(dto.getRol());
        usuario.setActivo(true);

        Usuario guardado = usuarioRepository.save(usuario);

        UsuarioResponseDto response = new UsuarioResponseDto();
        response.setId(guardado.getId());
        response.setNombre(guardado.getNombre());
        response.setEmail(guardado.getEmail());
        response.setRol(guardado.getRol());
        response.setActivo(guardado.getActivo());
        response.setCreadoEn(guardado.getCreadoEn());

        return response;
    }
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}