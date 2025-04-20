package com.ciroiencom.gamingheaventfc.service;

import com.ciroiencom.gamingheaventfc.model.Like;
import com.ciroiencom.gamingheaventfc.repository.LikeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LikeService {

/*    @Autowired
    private LikeRepository likeRepository;

    public Long save(LikeVO vO) {
        Like bean = new Like();
        BeanUtils.copyProperties(vO, bean);
        bean = likeRepository.save(bean);
        return bean.getId();
    }

    public void delete(Long id) {
        likeRepository.deleteById(id);
    }

    public void update(Long id, LikeUpdateVO vO) {
        Like bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        likeRepository.save(bean);
    }

    public LikeDTO getById(Long id) {
        Like original = requireOne(id);
        return toDTO(original);
    }

    public Page<LikeDTO> query(LikeQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private LikeDTO toDTO(Like original) {
        LikeDTO bean = new LikeDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Like requireOne(Long id) {
        return likeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    } */
}
