package com.example.alimentared.Dto.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EntregaRequestDto {
    @NotNull(message = "El ID de la solicitud es obligatorio")
    private Long solicitudId;
    private String observaciones;
}