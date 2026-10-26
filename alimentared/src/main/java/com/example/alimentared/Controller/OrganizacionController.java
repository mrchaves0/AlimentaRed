package com.example.alimentared.Controller;

import com.example.alimentared.Dto.Request.OrganizacionRequestDto;
import com.example.alimentared.Dto.Response.OrganizacionResponseDto;
import com.example.alimentared.Service.OrganizacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizaciones")
@RequiredArgsConstructor
public class OrganizacionController {

    private final OrganizacionService organizacionService;

    @PostMapping
    public ResponseEntity<OrganizacionResponseDto> crear(@Valid @RequestBody OrganizacionRequestDto dto) {
        return new ResponseEntity<>(organizacionService.crear(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrganizacionResponseDto>> listarTodas() {
        return new ResponseEntity<>(organizacionService.listarTodas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizacionResponseDto> obtenerPorId(@PathVariable Long id) {
        return new ResponseEntity<>(organizacionService.obtenerPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizacionResponseDto> actualizar(@PathVariable Long id, @Valid @RequestBody OrganizacionRequestDto dto) {
        return new ResponseEntity<>(organizacionService.actualizar(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        organizacionService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}