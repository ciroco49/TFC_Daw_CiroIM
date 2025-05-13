package com.ciroiencom.gamingheaventfc.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "correo")
    private String correo;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "img")
    private byte[] img;

    @Enumerated(EnumType.STRING)
    @Column(name="rol")
    private Rol rol;

    @OneToMany(mappedBy = "fkUserPk", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    /*public Usuario (String nickname, String email, String pass, Rol rol, byte[] img) {
        this.nickname = nickname;
        this.correo = email;
        this.password = pass;
        this.rol = rol;
        this.img = img;
    }*/

}
