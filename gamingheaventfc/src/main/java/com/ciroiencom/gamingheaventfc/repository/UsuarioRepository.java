package com.ciroiencom.gamingheaventfc.repository;

import com.ciroiencom.gamingheaventfc.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {
    Usuario findByNickname(String nickname);
    Usuario findByCorreo(String correo);
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.juegosFav WHERE u.nickname = :nickname")
    Usuario findByNicknameWithFavs(@Param("nickname") String nickname);

}