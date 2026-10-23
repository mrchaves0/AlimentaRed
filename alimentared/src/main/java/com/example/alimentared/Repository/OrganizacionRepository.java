package com.example.alimentared.Repository;


import com.example.alimentared.Entity.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizacionRepository extends JpaRepository<Organizacion, Long> {
}