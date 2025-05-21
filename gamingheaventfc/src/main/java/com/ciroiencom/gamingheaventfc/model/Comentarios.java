package com.ciroiencom.gamingheaventfc.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@Entity

@Table(name = "comentarios")
public class Comentarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "texto")
    private String texto;

    @ManyToOne
    @JoinColumn(name = "fk_user_pk", nullable = false)
    private Usuario user;

    @ManyToOne
    @JoinColumn(name = "fk_juego_pk", nullable = false)
    private Juego juego;

    @Column(name = "fecha")
    private Date fecha;

    public Comentarios() {}

    public Comentarios(Usuario user, Juego juego, String texto) {
        this.texto = texto;
        this.user = user;
        this.juego = juego;
        this.fecha = Date.from(LocalDateTime.now()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

}
