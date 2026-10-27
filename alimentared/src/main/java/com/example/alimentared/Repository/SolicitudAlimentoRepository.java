package com.example.alimentared.Repository;

import com.example.alimentared.Entity.SolicitudAlimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudAlimentoRepository extends JpaRepository<SolicitudAlimento, Long> {

    List<SolicitudAlimento> findByUsuarioId(Long usuarioId);


    List<SolicitudAlimento> findByLoteAlimentoId(Long loteId);
}
