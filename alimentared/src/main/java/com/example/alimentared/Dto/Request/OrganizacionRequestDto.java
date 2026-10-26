package com.example.alimentared.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrganizacionRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El NIT de identificación es obligatorio")
    private String nitIdentificacion;

    private String direccion;
    private BigDecimal latitud;
    private BigDecimal longitud;
    private String tipoOrganizacion;

    private Boolean esDonante = false;
    private Boolean esReceptora = false;

    @NotNull(message = "El ID del usuario responsable es obligatorio")
    private Long usuarioId;
}