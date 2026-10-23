package com.example.alimentared.Service;

import com.example.alimentared.Dto.Request.LoteAlimentoRequestDto;
import com.example.alimentared.Dto.Response.LoteAlimentoResponseDto;
import com.example.alimentared.Entity.CategoriaAlimento;
import com.example.alimentared.Entity.LoteAlimento;
import com.example.alimentared.Entity.Organizacion;
import com.example.alimentared.Repository.CategoriaAlimentoRepository;
import com.example.alimentared.Repository.LoteAlimentoRepository;
import com.example.alimentared.Repository.OrganizacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoteAlimentoService {

    private final LoteAlimentoRepository loteRepository;
    private final OrganizacionRepository organizacionRepository;
    private final CategoriaAlimentoRepository categoriaRepository;

    public LoteAlimentoResponseDto crearLote(LoteAlimentoRequestDto dto) {

        Organizacion organizacion = organizacionRepository.findById(dto.getOrganizacionId())
                .orElseThrow(() -> new RuntimeException("Organización no encontrada"));

        CategoriaAlimento categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        LoteAlimento lote = new LoteAlimento();
        lote.setTitulo(dto.getTitulo());
        lote.setDescripcion(dto.getDescripcion());
        lote.setCantidadTotal(dto.getCantidadTotal());
        lote.setCantidadDisponible(dto.getCantidadTotal());
        lote.setUnidadMedida(dto.getUnidadMedida());
        lote.setFechaExpiracion(dto.getFechaExpiracion());
        lote.setOrganizacion(organizacion);
        lote.setCategoria(categoria);

        LoteAlimento loteGuardado = loteRepository.save(lote);


        LoteAlimentoResponseDto response = new LoteAlimentoResponseDto();
        response.setId(loteGuardado.getId());
        response.setTitulo(loteGuardado.getTitulo());
        response.setDescripcion(loteGuardado.getDescripcion());
        response.setCantidadTotal(loteGuardado.getCantidadTotal());
        response.setCantidadDisponible(loteGuardado.getCantidadDisponible());
        response.setUnidadMedida(loteGuardado.getUnidadMedida());
        response.setFechaExpiracion(loteGuardado.getFechaExpiracion());
        response.setEstado(loteGuardado.getEstado());
        response.setNombreOrganizacion(loteGuardado.getOrganizacion().getNombre());
        response.setNombreCategoria(loteGuardado.getCategoria().getNombre());

        return response;
    }

    public List<LoteAlimentoResponseDto> listarLotes() {
        List<LoteAlimento> lotes = loteRepository.findAll();

        return lotes.stream().map(lote -> {
            LoteAlimentoResponseDto dto = new LoteAlimentoResponseDto();
            dto.setId(lote.getId());
            dto.setTitulo(lote.getTitulo());
            dto.setDescripcion(lote.getDescripcion());
            dto.setCantidadTotal(lote.getCantidadTotal());
            dto.setCantidadDisponible(lote.getCantidadDisponible());
            dto.setUnidadMedida(lote.getUnidadMedida());
            dto.setFechaExpiracion(lote.getFechaExpiracion());
            dto.setEstado(lote.getEstado());
            dto.setNombreOrganizacion(lote.getOrganizacion().getNombre());
            dto.setNombreCategoria(lote.getCategoria().getNombre());
            return dto;
        }).collect(Collectors.toList());
    }
}