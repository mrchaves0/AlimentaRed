package com.example.alimentared.Dto.Response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EntregaResponseDto {
    private Long id;
    private Long solicitudId;
    private String nombreUsuario;
    private LocalDateTime fechaEntrega;
    private String observaciones;
}