package com.example.alimentared.Controller;

import com.example.alimentared.Service.EntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/entregas")
@RequiredArgsConstructor
public class EntregaController {

    private final EntregaService entregaService;

    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<Map<String, String>> confirmarEntrega(@PathVariable Long id) {
        String mensaje = entregaService.confirmarRecepcion(id);


        Map<String, String> response = new HashMap<>();
        response.put("mensaje", mensaje);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}