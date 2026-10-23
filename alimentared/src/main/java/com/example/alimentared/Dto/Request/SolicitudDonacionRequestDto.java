package com.example.alimentared.Dto.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class SolicitudDonacionRequestDto {

    @NotNull(message = "El ID del lote es obligatorio")
    private Long loteId;

    @NotNull(message = "El ID de la organización solicitante es obligatorio")
    private Long organizacionId;

    @NotNull(message = "La cantidad solicitada es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private BigDecimal cantidadSolicitada;
}