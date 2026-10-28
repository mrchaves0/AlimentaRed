package com.example.alimentared.Controller;

import com.example.alimentared.Dto.Request.IncidenciaRequestDto;
import com.example.alimentared.Dto.Response.IncidenciaResponseDto;
import com.example.alimentared.Service.IncidenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    @PostMapping("/entregas/{idEntrega}/incidencias")
    public ResponseEntity<IncidenciaResponseDto> reportarPorEntrega(
            @PathVariable Long idEntrega,
            @Valid @RequestBody IncidenciaRequestDto dto) {
        dto.setEntregaId(idEntrega);
        IncidenciaResponseDto response = incidenciaService.reportarIncidencia(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/incidencias")
    public ResponseEntity<IncidenciaResponseDto> reportarIncidencia(@Valid @RequestBody IncidenciaRequestDto dto) {
        IncidenciaResponseDto response = incidenciaService.reportarIncidencia(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/entregas/{idEntrega}/incidencias")
    public ResponseEntity<List<IncidenciaResponseDto>> listarPorEntrega(@PathVariable Long idEntrega) {
        List<IncidenciaResponseDto> lista = incidenciaService.listarPorEntrega(idEntrega);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
