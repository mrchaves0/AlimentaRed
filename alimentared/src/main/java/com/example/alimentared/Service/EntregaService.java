package com.example.alimentared.Service;

import com.example.alimentared.Entity.Entrega;
import com.example.alimentared.Entity.EstadoEntrega;
import com.example.alimentared.Entity.SolicitudAlimento;
import com.example.alimentared.Repository.EntregaRepository;
import com.example.alimentared.Repository.SolicitudAlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private final SolicitudAlimentoRepository solicitudRepository;


    public Entrega programarEntrega(Long solicitudId, String responsableRetiro) {
        SolicitudAlimento solicitud = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        Entrega entrega = new Entrega();
        entrega.setSolicitud(solicitud);
        entrega.setResponsableRetiro(responsableRetiro);


        return entregaRepository.save(entrega);
    }


    public String confirmarRecepcion(Long id) {
        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada"));

        entrega.setEstado(EstadoEntrega.ENTREGADA);
        entrega.setFechaRecepcion(LocalDateTime.now());

        entregaRepository.save(entrega);

        return "Entrega confirmada exitosamente";
    }
}