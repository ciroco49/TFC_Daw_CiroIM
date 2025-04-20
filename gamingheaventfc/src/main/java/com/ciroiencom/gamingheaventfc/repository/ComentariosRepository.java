package com.ciroiencom.gamingheaventfc.repository;

import com.ciroiencom.gamingheaventfc.model.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ComentariosRepository extends JpaRepository<Comentarios, Long>, JpaSpecificationExecutor<Comentarios> {

}