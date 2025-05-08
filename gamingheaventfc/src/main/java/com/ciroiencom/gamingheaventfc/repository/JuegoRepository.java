package com.ciroiencom.gamingheaventfc.repository;

import com.ciroiencom.gamingheaventfc.model.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface JuegoRepository extends JpaRepository<Juego, Long>, JpaSpecificationExecutor<Juego> {
    Juego findByTitulo(String title);
    @Query(value = "SELECT DISTINCT genero FROM juego", nativeQuery = true)
    List<String> getAllGenresFromGames();
}