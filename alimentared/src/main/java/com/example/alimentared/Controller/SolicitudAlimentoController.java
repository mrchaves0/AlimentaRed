package com.example.alimentared.Controller;

import com.example.alimentared.Dto.Request.SolicitudAlimentoRequestDto;
import com.example.alimentared.Dto.Response.SolicitudAlimentoResponseDto;
import com.example.alimentared.Entity.EstadoSolicitud;
import com.example.alimentared.Service.SolicitudAlimentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudAlimentoController {

    private final SolicitudAlimentoService solicitudService;

    @PostMapping
    public ResponseEntity<SolicitudAlimentoResponseDto> crearSolicitud(
            @Valid @RequestBody SolicitudAlimentoRequestDto dto) {
        SolicitudAlimentoResponseDto nuevaSolicitud = solicitudService.crearSolicitud(dto);
        return new ResponseEntity<>(nuevaSolicitud, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<SolicitudAlimentoResponseDto>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<SolicitudAlimentoResponseDto> solicitudes = solicitudService.listarPorUsuario(usuarioId);
        return new ResponseEntity<>(solicitudes, HttpStatus.OK);
    }

    @GetMapping("/lote/{loteId}")
    public ResponseEntity<List<SolicitudAlimentoResponseDto>> listarPorLote(@PathVariable Long loteId) {
        List<SolicitudAlimentoResponseDto> solicitudes = solicitudService.listarPorLote(loteId);
        return new ResponseEntity<>(solicitudes, HttpStatus.OK);
    }
    @PutMapping("/{id}/estado")
    public ResponseEntity<SolicitudAlimentoResponseDto> cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoSolicitud estado) {

        SolicitudAlimentoResponseDto solicitudActualizada = solicitudService.cambiarEstado(id, estado);
        return new ResponseEntity<>(solicitudActualizada, HttpStatus.OK);
    }
}