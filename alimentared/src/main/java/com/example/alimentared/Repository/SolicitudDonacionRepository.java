package com.example.alimentared.Repository;

import com.example.alimentared.Entity.SolicitudDonacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudDonacionRepository extends JpaRepository<SolicitudDonacion, Long> {
}