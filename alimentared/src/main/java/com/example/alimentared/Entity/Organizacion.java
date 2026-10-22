package com.example.alimentared.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "organizaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(name = "nit_identificacion", nullable = false, length = 50, unique = true)
    private String nitIdentificacion;

    @Column(length = 255)
    private String direccion;


    @Column(precision = 10, scale = 7)
    private BigDecimal latitud;

    @Column(precision = 10, scale = 7)
    private BigDecimal longitud;

    @Column(name = "tipo_organizacion", length = 50)
    private String tipoOrganizacion;


    @Column(name = "es_donante", nullable = false)
    private Boolean esDonante = false;

    @Column(name = "es_receptora", nullable = false)
    private Boolean esReceptora = false;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}