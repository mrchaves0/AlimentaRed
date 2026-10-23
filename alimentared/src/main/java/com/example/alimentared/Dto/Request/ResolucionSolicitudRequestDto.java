package com.example.alimentared.Dto.Request;

import com.example.alimentared.Entity.EstadoSolicitud;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResolucionSolicitudRequestDto {

    @NotNull(message = "El estado de resolución es obligatorio (APROBADA o RECHAZADA)")
    private EstadoSolicitud estado;
}