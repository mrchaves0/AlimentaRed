package com.example.alimentared.Dto.Response;

import com.example.alimentared.Entity.EstadoSolicitud;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SolicitudAlimentoResponseDto {
    private Long id;
    private Long loteId;
    private String tituloLote;
    private Long usuarioId;
    private String nombreUsuario;
    private Double cantidadSolicitada;
    private EstadoSolicitud estado;
    private LocalDateTime fechaSolicitud;
}