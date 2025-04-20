package com.ciroiencom.gamingheaventfc.service;

import com.ciroiencom.gamingheaventfc.model.Juego;
import com.ciroiencom.gamingheaventfc.repository.JuegoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class JuegoService {

/*    @Autowired
    private JuegoRepository juegoRepository;

    public Long save(JuegoVO vO) {
        Juego bean = new Juego();
        BeanUtils.copyProperties(vO, bean);
        bean = juegoRepository.save(bean);
        return bean.getId();
    }

    public void delete(Long id) {
        juegoRepository.deleteById(id);
    }

    public void update(Long id, JuegoUpdateVO vO) {
        Juego bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        juegoRepository.save(bean);
    }

    public JuegoDTO getById(Long id) {
        Juego original = requireOne(id);
        return toDTO(original);
    }

    public Page<JuegoDTO> query(JuegoQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private JuegoDTO toDTO(Juego original) {
        JuegoDTO bean = new JuegoDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Juego requireOne(Long id) {
        return juegoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    } */
}
