package com.ciroiencom.gamingheaventfc.repository;

import com.ciroiencom.gamingheaventfc.model.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComentariosRepository extends JpaRepository<Comentarios, Long>, JpaSpecificationExecutor<Comentarios> {
    @Query(value = "SELECT u.nickname FROM Usuario u INNER JOIN Comentarios c ON u.id = c.fk_user_pk",
            nativeQuery = true)
    List<String> findAuthorsNamesFromComments();
}