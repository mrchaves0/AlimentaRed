package com.example.alimentared.Service;

import com.example.alimentared.Dto.Request.SolicitudAlimentoRequestDto;
import com.example.alimentared.Dto.Response.SolicitudAlimentoResponseDto;
import com.example.alimentared.Entity.EstadoSolicitud;
import com.example.alimentared.Entity.LoteAlimento;
import com.example.alimentared.Entity.SolicitudAlimento;
import com.example.alimentared.Entity.Usuario;
import com.example.alimentared.Repository.LoteAlimentoRepository;
import com.example.alimentared.Repository.SolicitudAlimentoRepository;
import com.example.alimentared.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SolicitudAlimentoService {

    private final SolicitudAlimentoRepository solicitudRepository;
    private final LoteAlimentoRepository loteRepository;
    private final UsuarioRepository usuarioRepository;

    public SolicitudAlimentoResponseDto crearSolicitud(SolicitudAlimentoRequestDto dto) {

        LoteAlimento lote = loteRepository.findById(dto.getLoteId())
                .orElseThrow(() -> new RuntimeException("Lote de alimento no encontrado"));


        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        if (lote.getCantidadDisponible().doubleValue() < dto.getCantidadSolicitada()) {
            throw new RuntimeException("No hay suficiente cantidad disponible en este lote");
        }


        SolicitudAlimento solicitud = new SolicitudAlimento();
        solicitud.setLoteAlimento(lote);
        solicitud.setUsuario(usuario);
        solicitud.setCantidadSolicitada(dto.getCantidadSolicitada());

        SolicitudAlimento solicitudGuardada = solicitudRepository.save(solicitud);

        return mapearAResponse(solicitudGuardada);
    }

    public List<SolicitudAlimentoResponseDto> listarPorUsuario(Long usuarioId) {
        return solicitudRepository.findByUsuarioId(usuarioId).stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    public List<SolicitudAlimentoResponseDto> listarPorLote(Long loteId) {
        return solicitudRepository.findByLoteAlimentoId(loteId).stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }


    private SolicitudAlimentoResponseDto mapearAResponse(SolicitudAlimento solicitud) {
        SolicitudAlimentoResponseDto dto = new SolicitudAlimentoResponseDto();
        dto.setId(solicitud.getId());
        dto.setLoteId(solicitud.getLoteAlimento().getId());
        dto.setTituloLote(solicitud.getLoteAlimento().getTitulo());
        dto.setUsuarioId(solicitud.getUsuario().getId());
        dto.setNombreUsuario(solicitud.getUsuario().getNombre());
        dto.setCantidadSolicitada(solicitud.getCantidadSolicitada());
        dto.setEstado(solicitud.getEstado());
        dto.setFechaSolicitud(solicitud.getFechaSolicitud());
        return dto;
    }

    @Transactional
    public SolicitudAlimentoResponseDto cambiarEstado(Long solicitudId, EstadoSolicitud nuevoEstado) {
        SolicitudAlimento solicitud = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        if (solicitud.getEstado() != EstadoSolicitud.PENDIENTE) {
            throw new RuntimeException("Esta solicitud ya fue procesada anteriormente");
        }

        if (nuevoEstado == EstadoSolicitud.APROBADA) {
            LoteAlimento lote = solicitud.getLoteAlimento();
            double disponible = lote.getCantidadDisponible().doubleValue();

            if (disponible < solicitud.getCantidadSolicitada()) {
                throw new RuntimeException("Inventario insuficiente para aprobar la solicitud");
            }


            lote.setCantidadDisponible(java.math.BigDecimal.valueOf(disponible - solicitud.getCantidadSolicitada()));
            loteRepository.save(lote);
        }

        solicitud.setEstado(nuevoEstado);
        SolicitudAlimento solicitudGuardada = solicitudRepository.save(solicitud);

        return mapearAResponse(solicitudGuardada);
    }
}