package com.ciroiencom.gamingheaventfc.repository;

import com.ciroiencom.gamingheaventfc.model.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface JuegoRepository extends JpaRepository<Juego, Long>, JpaSpecificationExecutor<Juego> {
    Juego findByTitulo(String title);
}