package com.example.alimentared.Dto.Request;


import lombok.Data;
import java.math.BigDecimal;

@Data
public class SolicitudDonacionRequestDto {

    private BigDecimal cantidadSolicitada;
    private String observaciones;

    private Long loteId;
    private Long organizacionId;
}