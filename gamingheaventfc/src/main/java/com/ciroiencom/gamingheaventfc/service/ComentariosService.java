package com.ciroiencom.gamingheaventfc.service;

import com.ciroiencom.gamingheaventfc.model.Comentarios;
import com.ciroiencom.gamingheaventfc.repository.ComentariosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ComentariosService {

/*    @Autowired
    private ComentariosRepository comentariosRepository;

    public Long save(ComentariosVO vO) {
        Comentarios bean = new Comentarios();
        BeanUtils.copyProperties(vO, bean);
        bean = comentariosRepository.save(bean);
        return bean.getId();
    }

    public void delete(Long id) {
        comentariosRepository.deleteById(id);
    }

    public void update(Long id, ComentariosUpdateVO vO) {
        Comentarios bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        comentariosRepository.save(bean);
    }

    public ComentariosDTO getById(Long id) {
        Comentarios original = requireOne(id);
        return toDTO(original);
    }

    public Page<ComentariosDTO> query(ComentariosQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private ComentariosDTO toDTO(Comentarios original) {
        ComentariosDTO bean = new ComentariosDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Comentarios requireOne(Long id) {
        return comentariosRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    } */
}
