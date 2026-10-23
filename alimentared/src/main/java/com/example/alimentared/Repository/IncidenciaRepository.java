package com.example.alimentared.Repository;

import com.example.alimentared.Entity.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long>{

}
