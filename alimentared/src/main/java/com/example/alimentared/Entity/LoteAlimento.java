package com.example.alimentared.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "lotes_alimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoteAlimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(length = 500)
    private String descripcion;


    @Column(name = "cantidad_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidadTotal;

    @Column(name = "cantidad_disponible", nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidadDisponible;

    @Column(name = "unidad_medida", nullable = false, length = 20)
    private String unidadMedida;

    @Column(name = "fecha_expiracion", nullable = false)
    private LocalDateTime fechaExpiracion;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoLote estado;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaAlimento categoria;

    @ManyToOne
    @JoinColumn(name = "organizacion_id", nullable = false)
    private Organizacion organizacion;

    @PrePersist
    protected void onCreate() {
        this.creadoEn = LocalDateTime.now();

        if (this.estado == null) {
            this.estado = EstadoLote.DISPONIBLE;
        }
    }
}