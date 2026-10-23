package com.example.alimentared.Repository;
import com.example.alimentared.Entity.CategoriaAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaAlimentoRepository extends JpaRepository<CategoriaAlimento, Long>{

}
