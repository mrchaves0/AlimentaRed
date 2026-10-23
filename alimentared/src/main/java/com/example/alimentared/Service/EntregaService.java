package com.example.alimentared.Service;

import com.example.alimentared.Entity.Entrega;
import com.example.alimentared.Entity.EstadoEntrega;
import com.example.alimentared.Repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EntregaService {

    private final EntregaRepository entregaRepository;

    public String confirmarRecepcion(Long id) {
        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada"));


        entrega.setEstado(EstadoEntrega.ENTREGADA);
        entrega.setFechaRecepcion(LocalDateTime.now());

        entregaRepository.save(entrega);

        return "Entrega confirmada exitosamente";
    }
}