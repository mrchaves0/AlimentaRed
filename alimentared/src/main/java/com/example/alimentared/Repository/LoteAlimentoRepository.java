package com.example.alimentared.Repository;

import com.example.alimentared.Entity.EstadoLote;
import com.example.alimentared.Entity.LoteAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoteAlimentoRepository extends JpaRepository<LoteAlimento, Long> {
    List<LoteAlimento> findByEstado(EstadoLote estado);
}