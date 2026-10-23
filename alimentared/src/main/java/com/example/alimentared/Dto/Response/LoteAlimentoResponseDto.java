package com.example.alimentared.Dto.Response;

import com.example.alimentared.Entity.EstadoLote;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoteAlimentoResponseDto {

    private Long id;
    private String titulo;
    private String descripcion;
    private BigDecimal cantidadTotal;
    private BigDecimal cantidadDisponible;
    private String unidadMedida;
    private LocalDateTime fechaExpiracion;
    private EstadoLote estado;

    private String nombreOrganizacion;
    private String nombreCategoria;
}
