package com.ciroiencom.gamingheaventfc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Email can´t be empty")
    @Pattern(regexp = "^[a-zA-Z0-9.!¡#$%&'*+\\/=¿?^_`\\{|\\}~-]{1,64}@([a-zA-Z0-9-]{1,63}\\.){1,}[a-zA-Z]{2,63}$"
            , message = "The email must be in the usual email format and have a maximum length of 320 characters."
            , groups = {ValidationGroups.Register.class, ValidationGroups.Login.class})
    @Column(name = "correo")
    private String correo;

    @NotBlank(message = "Password can´t be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[\\w@\\.,\\/!?#$%^&*()-+=]{8,12}$"
            , message = "The password must contain a capital letter, a number and be between 8 and 12 characters long."
            , groups = {ValidationGroups.Register.class, ValidationGroups.Login.class})
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Nickname can´t be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9_]{0,19}$"
            , message = "The nickname must start with a capital letter and have a maximum length of 20 characters."
            , groups = {ValidationGroups.Register.class})
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

}
