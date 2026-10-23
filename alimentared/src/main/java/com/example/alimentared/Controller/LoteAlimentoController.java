package com.example.alimentared.Controller;

import com.example.alimentared.Dto.Request.LoteAlimentoRequestDto;
import com.example.alimentared.Dto.Response.LoteAlimentoResponseDto;
import com.example.alimentared.Entity.EstadoLote;
import com.example.alimentared.Service.LoteAlimentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lotes")
@RequiredArgsConstructor
public class LoteAlimentoController {

    private final LoteAlimentoService loteService;

    @GetMapping
    public ResponseEntity<List<LoteAlimentoResponseDto>> listarLotes(
            @RequestParam(required = false) EstadoLote estado) {

        List<LoteAlimentoResponseDto> lotes = loteService.listarLotes(estado);
        return new ResponseEntity<>(lotes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteAlimentoResponseDto> obtenerLote(@PathVariable Long id) {
        LoteAlimentoResponseDto lote = loteService.obtenerLotePorId(id);
        return new ResponseEntity<>(lote, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LoteAlimentoResponseDto> crearLote(@Valid @RequestBody LoteAlimentoRequestDto dto) {
        LoteAlimentoResponseDto nuevoLote = loteService.crearLote(dto);
        return new ResponseEntity<>(nuevoLote, HttpStatus.CREATED);
    }
}