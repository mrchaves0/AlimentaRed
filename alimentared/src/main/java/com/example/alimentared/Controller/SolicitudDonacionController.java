package com.example.alimentared.Controller;

import com.example.alimentared.Dto.Request.ResolucionSolicitudRequestDto;
import com.example.alimentared.Dto.Request.SolicitudDonacionRequestDto;
import com.example.alimentared.Dto.Response.SolicitudDonacionResponseDto;
import com.example.alimentared.Service.SolicitudDonacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudDonacionController {

    private final SolicitudDonacionService solicitudService;

    @PostMapping
    public ResponseEntity<SolicitudDonacionResponseDto> crearSolicitud(@Valid @RequestBody SolicitudDonacionRequestDto dto) {
        SolicitudDonacionResponseDto nuevaSolicitud = solicitudService.crearSolicitud(dto);
        return new ResponseEntity<>(nuevaSolicitud, HttpStatus.CREATED);
    }
    @PatchMapping("/{id}/resolver")
    public ResponseEntity<SolicitudDonacionResponseDto> resolverSolicitud(
            @PathVariable Long id,
            @Valid @RequestBody ResolucionSolicitudRequestDto dto) {
        SolicitudDonacionResponseDto solicitudResuelta = solicitudService.resolverSolicitud(id, dto);
        return new ResponseEntity<>(solicitudResuelta, HttpStatus.OK);
    }
}
