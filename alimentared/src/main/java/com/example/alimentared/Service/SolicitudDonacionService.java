package com.example.alimentared.Service;

import com.example.alimentared.Dto.Request.ResolucionSolicitudRequestDto;
import com.example.alimentared.Dto.Request.SolicitudDonacionRequestDto;
import com.example.alimentared.Dto.Response.SolicitudDonacionResponseDto;
import com.example.alimentared.Entity.EstadoSolicitud;
import com.example.alimentared.Entity.LoteAlimento;
import com.example.alimentared.Entity.Organizacion;
import com.example.alimentared.Entity.SolicitudDonacion;
import com.example.alimentared.Repository.LoteAlimentoRepository;
import com.example.alimentared.Repository.OrganizacionRepository;
import com.example.alimentared.Repository.SolicitudDonacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SolicitudDonacionService {

    private final SolicitudDonacionRepository solicitudRepository;
    private final LoteAlimentoRepository loteRepository;
    private final OrganizacionRepository organizacionRepository;

    public SolicitudDonacionResponseDto crearSolicitud(SolicitudDonacionRequestDto dto) {
        LoteAlimento lote = loteRepository.findById(dto.getLoteId())
                .orElseThrow(() -> new RuntimeException("Lote de alimento no encontrado"));

        Organizacion organizacion = organizacionRepository.findById(dto.getOrganizacionId())
                .orElseThrow(() -> new RuntimeException("Organización no encontrada"));

        if (lote.getCantidadDisponible().compareTo(dto.getCantidadSolicitada()) < 0) {
            throw new RuntimeException("No hay suficiente cantidad disponible en este lote para tu solicitud");
        }

        SolicitudDonacion solicitud = new SolicitudDonacion();
        solicitud.setCantidadSolicitada(dto.getCantidadSolicitada());
        solicitud.setFechaSolicitud(LocalDateTime.now());
        solicitud.setEstado(EstadoSolicitud.PENDIENTE);
        solicitud.setLoteAlimento(lote);
        solicitud.setOrganizacion(organizacion);

        SolicitudDonacion solicitudGuardada = solicitudRepository.save(solicitud);
        return mapearADto(solicitudGuardada);
    }

    public SolicitudDonacionResponseDto resolverSolicitud(Long id, ResolucionSolicitudRequestDto dto) {
        SolicitudDonacion solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        if (solicitud.getEstado() != EstadoSolicitud.PENDIENTE) {
            throw new RuntimeException("Esta solicitud ya fue resuelta anteriormente");
        }


        if (dto.getEstado() == EstadoSolicitud.APROBADA) {
            LoteAlimento lote = solicitud.getLoteAlimento();

            if (lote.getCantidadDisponible().compareTo(solicitud.getCantidadSolicitada()) < 0) {
                throw new RuntimeException("Ya no hay suficiente cantidad para aprobar esta solicitud");
            }

            lote.setCantidadDisponible(lote.getCantidadDisponible().subtract(solicitud.getCantidadSolicitada()));
            loteRepository.save(lote);
        }

        solicitud.setEstado(dto.getEstado());
        SolicitudDonacion solicitudActualizada = solicitudRepository.save(solicitud);

        return mapearADto(solicitudActualizada);
    }

    private SolicitudDonacionResponseDto mapearADto(SolicitudDonacion solicitud) {
        SolicitudDonacionResponseDto response = new SolicitudDonacionResponseDto();
        response.setId(solicitud.getId());
        response.setCantidadSolicitada(solicitud.getCantidadSolicitada());
        response.setEstado(solicitud.getEstado());
        response.setFechaSolicitud(solicitud.getFechaSolicitud());
        response.setTituloLote(solicitud.getLoteAlimento().getTitulo());
        response.setNombreOrganizacionSolicitante(solicitud.getOrganizacion().getNombre());
        return response;
    }
}