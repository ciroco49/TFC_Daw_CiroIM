package com.ciroiencom.gamingheaventfc.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
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

    @Column(name = "fk_user_pk", nullable = false)
    private String fkUserPk;

    @Column(name = "fk_juego_pk", nullable = false)
    private Integer fkJuegoPk;

    @Column(name = "fecha")
    private Date fecha;

}
