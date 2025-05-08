package com.ciroiencom.gamingheaventfc.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "likes")
public class Like implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fkUserPk", referencedColumnName = "id", nullable = false)
    private Usuario fkUserPk;

    @ManyToOne
    @JoinColumn(name = "fkJuegoPk", referencedColumnName = "id_pk", nullable = false)
    private Juego fkJuegoPk;

}
