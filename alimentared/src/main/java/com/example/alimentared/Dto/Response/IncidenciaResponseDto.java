package com.example.alimentared.Dto.Response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class IncidenciaResponseDto {
    private Long id;
    private String motivo;
    private String descripcion;
    private LocalDateTime fechaReporte;
    private Boolean resuelta;
    private Long entregaId;
    private Long usuarioId;
}
