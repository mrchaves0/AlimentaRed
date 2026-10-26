package com.example.alimentared.Dto.Response;

import com.example.alimentared.Entity.Rol;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UsuarioResponseDto {
    private Long id;
    private String nombre;
    private String email;
    private Rol rol;
    private Boolean activo;
    private LocalDateTime creadoEn;
}