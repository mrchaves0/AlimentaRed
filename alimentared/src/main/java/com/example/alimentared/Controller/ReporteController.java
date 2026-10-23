package com.example.alimentared.Controller;

import com.example.alimentared.Dto.Response.ReporteImpactoResponseDto;
import com.example.alimentared.Service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping("/impacto")
    public ResponseEntity<ReporteImpactoResponseDto> obtenerImpacto() {
        ReporteImpactoResponseDto reporte = reporteService.generarReporte();
        return new ResponseEntity<>(reporte, HttpStatus.OK);
    }
}