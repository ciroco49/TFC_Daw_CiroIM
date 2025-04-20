package com.ciroiencom.gamingheaventfc.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "juego")
public class Juego implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_api", nullable = false)
    private Integer idApi;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion_S")
    private String descripcionS;

    @Column(name = "descripcion_L")
    private String descripcionL;

    @Column(name = "genero")
    private String genero;

    @Column(name = "plataforma")
    private String plataforma;

    @Column(name = "distribuidor")
    private String distribuidor;

    @Column(name = "desarrollador")
    private String desarrollador;

    @Column(name = "fecha_salida")
    private Date fechaSalida;

    @Column(name = "OS")
    private String os;

    @Column(name = "procesador")
    private String procesador;

    @Column(name = "memoria")
    private String memoria;

    @Column(name = "grafica")
    private String grafica;

    @Column(name = "almacenamiento")
    private String almacenamiento;

}
