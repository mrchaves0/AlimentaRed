package com.example.alimentared.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoteAlimentoRequestDto {

    @NotBlank(message = "El título no puede estar vacío")
    private String titulo;

    private String descripcion;

    @NotNull(message = "La cantidad total es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private BigDecimal cantidadTotal;

    @NotBlank(message = "La unidad de medida es obligatoria")
    private String unidadMedida;

    @NotNull(message = "La fecha de expiración es obligatoria")
    private LocalDateTime fechaExpiracion;

    @NotNull(message = "El ID de la organización es obligatorio")
    private Long organizacionId;

    @NotNull(message = "El ID de la categoría es obligatorio")
    private Long categoriaId;
}