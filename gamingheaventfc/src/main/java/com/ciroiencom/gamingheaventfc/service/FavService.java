package com.ciroiencom.gamingheaventfc.service;

import com.ciroiencom.gamingheaventfc.model.Fav;
import com.ciroiencom.gamingheaventfc.repository.FavRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class FavService {

    @Autowired
    private FavRepository favRepository;

    public void save(Fav fav) {
        favRepository.save(fav);
    }

    public void delete(Long id) {
        favRepository.deleteById(id);
    }

    public ArrayList<Fav> getAll() {
        ArrayList<Fav> favs = new ArrayList(favRepository.findAll());
        return favs;
    }

}
