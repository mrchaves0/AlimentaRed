package com.example.alimentared.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IncidenciaRequestDto {

    @NotBlank(message = "El motivo es obligatorio")
    private String motivo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    private Long entregaId;

    private Long usuarioId;
}
