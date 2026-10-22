package com.example.alimentared.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "entregas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEntrega estado;

    @Column(name = "fecha_retiro")
    private LocalDateTime fechaRetiro;

    @Column(name = "fecha_recepcion")
    private LocalDateTime fechaRecepcion;

    @Column(name = "responsable_retiro", length = 100)
    private String responsableRetiro;

    @Column(name = "responsable_recepcion", length = 100)
    private String responsableRecepcion;

    @Column(length = 500)
    private String notas;
    @OneToOne
    @JoinColumn(name = "solicitud_id", nullable = false)
    private SolicitudDonacion solicitud;

    @PrePersist
    protected void onCreate() {
        if (this.estado == null) {
            this.estado = EstadoEntrega.PROGRAMADA;
        }
    }
}