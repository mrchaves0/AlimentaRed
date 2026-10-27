package com.example.alimentared.Dto.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SolicitudAlimentoRequestDto {

    @NotNull(message = "El ID del lote es obligatorio")
    private Long loteId;


    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "La cantidad solicitada es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Double cantidadSolicitada;
}