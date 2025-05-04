package com.ciroiencom.gamingheaventfc.service;

import com.ciroiencom.gamingheaventfc.model.Juego;
import com.ciroiencom.gamingheaventfc.model.Like;
import com.ciroiencom.gamingheaventfc.model.Usuario;
import com.ciroiencom.gamingheaventfc.repository.LikeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public void saveOrUpdate(Juego videogame, Usuario usr) {
        //TODO - Todavía no guardo likes
    }

    public void delete(Long id) {
        //TODO - Todavía no borro likes
    }

    public ArrayList<Like> getAll() {
        ArrayList<Like> likes = new ArrayList(likeRepository.findAll());
        return likes;
    }

}
