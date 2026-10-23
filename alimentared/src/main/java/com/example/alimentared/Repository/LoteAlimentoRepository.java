package com.example.alimentared.Repository;

import com.example.alimentared.Entity.LoteAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteAlimentoRepository extends JpaRepository<LoteAlimento, Long> {
}