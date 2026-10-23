package com.example.alimentared.Dto.Request;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoteAlimentoRequestDto {

    private String titulo;
    private String descripcion;
    private BigDecimal cantidadTotal;
    private String unidadMedida;
    private LocalDateTime fechaExpiracion;

    private Long organizacionId;
    private Long categoriaId;
}