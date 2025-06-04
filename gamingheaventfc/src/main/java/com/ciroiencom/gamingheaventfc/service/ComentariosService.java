package com.ciroiencom.gamingheaventfc.service;

import com.ciroiencom.gamingheaventfc.model.Comentarios;
import com.ciroiencom.gamingheaventfc.repository.ComentariosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ComentariosService {

    @Autowired
    ComentariosRepository comentariosRepository;

    public void save(Comentarios comment) {
        comentariosRepository.save(comment);
    }

    public List<Comentarios> findAll() {
        return comentariosRepository.findAll();
    }

    public List<Comentarios> findByVideogameTitle(String title) {
        return comentariosRepository.findByVideogameTitle(title);
    }

}
