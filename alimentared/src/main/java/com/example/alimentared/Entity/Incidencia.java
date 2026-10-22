package com.example.alimentared.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String motivo;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(name = "fecha_reporte", updatable = false)
    private LocalDateTime fechaReporte;

    @Column(nullable = false)
    private Boolean resuelta = false;
    @ManyToOne
    @JoinColumn(name = "entrega_id", nullable = false)
    private Entrega entrega;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario reportadaPor;

    @PrePersist
    protected void onCreate() {
        this.fechaReporte = LocalDateTime.now();
    }
}