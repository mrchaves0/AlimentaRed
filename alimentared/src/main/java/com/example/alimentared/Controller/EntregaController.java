package com.example.alimentared.Controller;

import com.example.alimentared.Entity.Entrega;
import com.example.alimentared.Service.EntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entregas")
@RequiredArgsConstructor
public class EntregaController {

    private final EntregaService entregaService;


    @PostMapping("/programar")
    public ResponseEntity<Entrega> programarEntrega(
            @RequestParam Long solicitudId,
            @RequestParam String responsableRetiro) {
        Entrega nuevaEntrega = entregaService.programarEntrega(solicitudId, responsableRetiro);
        return new ResponseEntity<>(nuevaEntrega, HttpStatus.CREATED);
    }


    @PutMapping("/{id}/confirmar")
    public ResponseEntity<String> confirmarRecepcion(@PathVariable Long id) {
        String respuesta = entregaService.confirmarRecepcion(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}