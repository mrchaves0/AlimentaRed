package com.example.alimentared.Service;

import com.example.alimentared.Dto.Request.IncidenciaRequestDto;
import com.example.alimentared.Dto.Response.IncidenciaResponseDto;
import com.example.alimentared.Entity.Entrega;
import com.example.alimentared.Entity.Incidencia;
import com.example.alimentared.Entity.Usuario;
import com.example.alimentared.Repository.EntregaRepository;
import com.example.alimentared.Repository.IncidenciaRepository;
import com.example.alimentared.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;
    private final EntregaRepository entregaRepository;
    private final UsuarioRepository usuarioRepository;

    public IncidenciaResponseDto reportarIncidencia(IncidenciaRequestDto dto) {
        if (dto.getEntregaId() == null) {
            throw new RuntimeException("El ID de la entrega es obligatorio");
        }

        Entrega entrega = entregaRepository.findById(dto.getEntregaId())
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada con ID: " + dto.getEntregaId()));

        Usuario usuario = null;
        if (dto.getUsuarioId() != null) {
            usuario = usuarioRepository.findById(dto.getUsuarioId()).orElse(null);
        }

        Incidencia incidencia = new Incidencia();
        incidencia.setMotivo(dto.getMotivo());
        incidencia.setDescripcion(dto.getDescripcion());
        incidencia.setEntrega(entrega);
        incidencia.setReportadaPor(usuario);
        incidencia.setResuelta(false);

        Incidencia guardada = incidenciaRepository.save(incidencia);
        return mapToDto(guardada);
    }

    public List<IncidenciaResponseDto> listarPorEntrega(Long entregaId) {
        return incidenciaRepository.findAll().stream()
                .filter(i -> i.getEntrega() != null && i.getEntrega().getId().equals(entregaId))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private IncidenciaResponseDto mapToDto(Incidencia entidad) {
        IncidenciaResponseDto dto = new IncidenciaResponseDto();
        dto.setId(entidad.getId());
        dto.setMotivo(entidad.getMotivo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setFechaReporte(entidad.getFechaReporte());
        dto.setResuelta(entidad.getResuelta());
        if (entidad.getEntrega() != null) {
            dto.setEntregaId(entidad.getEntrega().getId());
        }
        if (entidad.getReportadaPor() != null) {
            dto.setUsuarioId(entidad.getReportadaPor().getId());
        }
        return dto;
    }
}
