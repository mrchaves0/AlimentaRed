package com.example.alimentared.Controller;

import com.example.alimentared.Entity.CategoriaAlimento;
import com.example.alimentared.Repository.CategoriaAlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaAlimentoController {

    private final CategoriaAlimentoRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<CategoriaAlimento>> listar() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<CategoriaAlimento> crear(@RequestBody CategoriaAlimento categoria) {
        return new ResponseEntity<>(categoriaRepository.save(categoria), HttpStatus.CREATED);
    }
}
