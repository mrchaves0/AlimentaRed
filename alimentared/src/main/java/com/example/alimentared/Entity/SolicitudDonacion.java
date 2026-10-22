package com.example.alimentared.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes_donacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDonacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad_solicitada", nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidadSolicitada;

    @Column(length = 500)
    private String observaciones;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSolicitud estado;

    @Column(name = "fecha_solicitud", updatable = false)
    private LocalDateTime fechaSolicitud;

    @ManyToOne
    @JoinColumn(name = "lote_id", nullable = false)
    private LoteAlimento loteAlimento;

    @ManyToOne
    @JoinColumn(name = "organizacion_id", nullable = false)
    private Organizacion organizacion;

    @PrePersist
    protected void onCreate() {
        this.fechaSolicitud = LocalDateTime.now();

        if (this.estado == null) {
            this.estado = EstadoSolicitud.PENDIENTE;
        }
    }
}