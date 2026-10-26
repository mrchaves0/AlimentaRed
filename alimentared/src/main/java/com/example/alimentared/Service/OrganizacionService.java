package com.example.alimentared.Service;

import com.example.alimentared.Dto.Request.OrganizacionRequestDto;
import com.example.alimentared.Dto.Response.OrganizacionResponseDto;
import com.example.alimentared.Entity.Organizacion;
import com.example.alimentared.Entity.Usuario;
import com.example.alimentared.Repository.OrganizacionRepository;
import com.example.alimentared.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizacionService {

    private final OrganizacionRepository organizacionRepository;
    private final UsuarioRepository usuarioRepository;

    public OrganizacionResponseDto crear(OrganizacionRequestDto dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Organizacion organizacion = new Organizacion();
        mapearEntidad(organizacion, dto);
        organizacion.setUsuario(usuario);

        Organizacion guardada = organizacionRepository.save(organizacion);
        return mapearADto(guardada);
    }

    public List<OrganizacionResponseDto> listarTodas() {
        return organizacionRepository.findAll().stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    public OrganizacionResponseDto obtenerPorId(Long id) {
        Organizacion organizacion = organizacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organización no encontrada"));
        return mapearADto(organizacion);
    }

    public OrganizacionResponseDto actualizar(Long id, OrganizacionRequestDto dto) {
        Organizacion organizacion = organizacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organización no encontrada"));


        mapearEntidad(organizacion, dto);

        Organizacion actualizada = organizacionRepository.save(organizacion);
        return mapearADto(actualizada);
    }

    public void eliminar(Long id) {
        if (!organizacionRepository.existsById(id)) {
            throw new RuntimeException("Organización no encontrada");
        }
        organizacionRepository.deleteById(id);
    }


    private void mapearEntidad(Organizacion organizacion, OrganizacionRequestDto dto) {
        organizacion.setNombre(dto.getNombre());
        organizacion.setNitIdentificacion(dto.getNitIdentificacion());
        organizacion.setDireccion(dto.getDireccion());
        organizacion.setLatitud(dto.getLatitud());
        organizacion.setLongitud(dto.getLongitud());
        organizacion.setTipoOrganizacion(dto.getTipoOrganizacion());
        if (dto.getEsDonante() != null) organizacion.setEsDonante(dto.getEsDonante());
        if (dto.getEsReceptora() != null) organizacion.setEsReceptora(dto.getEsReceptora());
    }

    private OrganizacionResponseDto mapearADto(Organizacion organizacion) {
        OrganizacionResponseDto dto = new OrganizacionResponseDto();
        dto.setId(organizacion.getId());
        dto.setNombre(organizacion.getNombre());
        dto.setNitIdentificacion(organizacion.getNitIdentificacion());
        dto.setDireccion(organizacion.getDireccion());
        dto.setLatitud(organizacion.getLatitud());
        dto.setLongitud(organizacion.getLongitud());
        dto.setTipoOrganizacion(organizacion.getTipoOrganizacion());
        dto.setEsDonante(organizacion.getEsDonante());
        dto.setEsReceptora(organizacion.getEsReceptora());
        dto.setUsuarioId(organizacion.getUsuario().getId());
        return dto;
    }
}