package com.example.alimentared.Dto.Response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrganizacionResponseDto {
    private Long id;
    private String nombre;
    private String nitIdentificacion;
    private String direccion;
    private BigDecimal latitud;
    private BigDecimal longitud;
    private String tipoOrganizacion;
    private Boolean esDonante;
    private Boolean esReceptora;
    private Long usuarioId;
}