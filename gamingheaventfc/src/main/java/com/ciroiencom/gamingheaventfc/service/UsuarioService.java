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

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findByNickname(String nickname) {
        return usuarioRepository.findByNickname(nickname);
    }

    public Usuario findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public void save(Usuario user) {
        usuarioRepository.save(user);
    }

    public void delete(Usuario user) {
        usuarioRepository.delete(user);
    }

   /* public void update(Long id, UsuarioUpdateVO vO) {
        Usuario bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        usuarioRepository.save(bean);
    } */

}
