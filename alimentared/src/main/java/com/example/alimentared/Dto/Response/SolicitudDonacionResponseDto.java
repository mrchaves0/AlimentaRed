package com.example.alimentared.Dto.Response;

import com.example.alimentared.Entity.EstadoSolicitud;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SolicitudDonacionResponseDto {
    private Long id;
    private BigDecimal cantidadSolicitada;
    private EstadoSolicitud estado;
    private LocalDateTime fechaSolicitud;
    private String tituloLote;
    private String nombreOrganizacionSolicitante;
}