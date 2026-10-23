package com.example.alimentared.Controller;


import com.example.alimentared.Dto.Request.LoteAlimentoRequestDto;
import com.example.alimentared.Dto.Response.LoteAlimentoResponseDto;
import com.example.alimentared.Service.LoteAlimentoService;
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

    @PostMapping
    public ResponseEntity<LoteAlimentoResponseDto> crearLote(@RequestBody LoteAlimentoRequestDto dto) {
        LoteAlimentoResponseDto nuevoLote = loteService.crearLote(dto);

        return new ResponseEntity<>(nuevoLote, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LoteAlimentoResponseDto>> listarLotes() {
        List<LoteAlimentoResponseDto> lotes = loteService.listarLotes();
        return new ResponseEntity<>(lotes, HttpStatus.OK);
    }
}