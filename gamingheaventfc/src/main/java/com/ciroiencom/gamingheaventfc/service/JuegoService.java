package com.ciroiencom.gamingheaventfc.service;

import com.ciroiencom.gamingheaventfc.model.Fav;
import com.ciroiencom.gamingheaventfc.model.Juego;
import com.ciroiencom.gamingheaventfc.repository.FavRepository;
import com.ciroiencom.gamingheaventfc.repository.JuegoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class JuegoService {

    @Autowired
    private JuegoRepository juegoRepository;

    public void saveOrUpdate(Juego videogameAPI) {
        Juego juego = juegoRepository.findByTitulo(videogameAPI.getTitulo());

        //Insertar juego
        if(juego == null) {
           juegoRepository.save(videogameAPI);
           return ;
        }

        //Updatear juego
        juego.setIdApi(videogameAPI.getIdApi());
        juego.setTitulo(videogameAPI.getTitulo());
        juego.setDescripcionS(videogameAPI.getDescripcionS());
        juego.setDescripcionL(videogameAPI.getDescripcionL());
        juego.setGenero(videogameAPI.getGenero());
        juego.setPlataforma(videogameAPI.getPlataforma());
        juego.setDistribuidor(videogameAPI.getDistribuidor());
        juego.setDesarrollador(videogameAPI.getDesarrollador());
        juego.setFechaSalida(videogameAPI.getFechaSalida());
        juego.setOs(videogameAPI.getOs());
        juego.setProcesador(videogameAPI.getProcesador());
        juego.setMemoria(videogameAPI.getMemoria());
        juego.setGrafica(videogameAPI.getGrafica());
        juego.setAlmacenamiento(videogameAPI.getAlmacenamiento());

        juegoRepository.save(juego);
    }

    public void delete(Long id) {
        juegoRepository.deleteById(id);
    }

    public ArrayList<Juego> getAll() {
        ArrayList<Juego> videogames = new ArrayList(juegoRepository.findAll());
        return videogames;
    }

    public ArrayList<String> getAllGenresFromGames() {
        ArrayList<String> genres = new ArrayList(juegoRepository.getAllGenresFromGames());
        return genres;
    }

}
