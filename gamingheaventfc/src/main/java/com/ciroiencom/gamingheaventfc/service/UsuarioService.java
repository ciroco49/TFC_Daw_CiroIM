package com.ciroiencom.gamingheaventfc.service;

import com.ciroiencom.gamingheaventfc.model.Usuario;
import com.ciroiencom.gamingheaventfc.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UsuarioService {

/*    @Autowired
    private UsuarioRepository usuarioRepository;

    public Long save(UsuarioVO vO) {
        Usuario bean = new Usuario();
        BeanUtils.copyProperties(vO, bean);
        bean = usuarioRepository.save(bean);
        return bean.getId();
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public void update(Long id, UsuarioUpdateVO vO) {
        Usuario bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        usuarioRepository.save(bean);
    }

    public UsuarioDTO getById(Long id) {
        Usuario original = requireOne(id);
        return toDTO(original);
    }

    public Page<UsuarioDTO> query(UsuarioQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UsuarioDTO toDTO(Usuario original) {
        UsuarioDTO bean = new UsuarioDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Usuario requireOne(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    } */
}
