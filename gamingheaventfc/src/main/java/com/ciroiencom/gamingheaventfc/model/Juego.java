package com.ciroiencom.gamingheaventfc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "juego")
public class Juego implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_pk", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pk;

    @JsonProperty("id")
    @Column(name = "id_api", nullable = false)
    private Integer idApi;

    @JsonProperty("title")
    @Column(name = "titulo")
    private String titulo;

    @JsonProperty("short_description")
    @Column(name = "descripcion_S")
    private String descripcionS;

    @JsonProperty("description")
    @Column(name = "descripcion_L")
    private String descripcionL;

    @JsonProperty("genre")
    @Column(name = "genero")
    private String genero;

    @JsonProperty("platform")
    @Column(name = "plataforma")
    private String plataforma;

    @JsonProperty("publisher")
    @Column(name = "distribuidor")
    private String distribuidor;

    @JsonProperty("developer")
    @Column(name = "desarrollador")
    private String desarrollador;

    @JsonProperty("release_date")
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

    @OneToMany(mappedBy = "fkJuegoPk", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    @JsonProperty("minimum_system_requirements")
    public void setRequisitosMinimos(SystemRequirements requisitos) {
        if (requisitos != null) {
            this.os = requisitos.getOs();
            this.procesador = requisitos.getProcessor();
            this.memoria = requisitos.getMemory();
            this.grafica = requisitos.getGraphics();
            this.almacenamiento = requisitos.getStorage();
        }
    }

    @Data
    private static class SystemRequirements {
        @JsonProperty("os")
        private String os;

        @JsonProperty("processor")
        private String processor;

        @JsonProperty("memory")
        private String memory;

        @JsonProperty("graphics")
        private String graphics;

        @JsonProperty("storage")
        private String storage;
    }

}
