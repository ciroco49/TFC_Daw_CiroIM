package com.ciroiencom.gamingheaventfc.service;

import com.ciroiencom.gamingheaventfc.model.Usuario;
import com.ciroiencom.gamingheaventfc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> findById(Long idUser) {
        return usuarioRepository.findById(idUser);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findByNickname(String nickname) {
        return usuarioRepository.findByNickname(nickname);
    }

    public Usuario findByNicknameWithFavs(String nickname) {
        return usuarioRepository.findByNicknameWithFavs(nickname);
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


   public void update(String nickname, Usuario usuario) {
        Usuario userFromBBDD = usuarioRepository.findByNickname(nickname);
            userFromBBDD.setImg(usuario.getImg());
            userFromBBDD.setNickname(usuario.getNickname());
            userFromBBDD.setDescripcion(usuario.getDescripcion());
        usuarioRepository.save(userFromBBDD);
    }

}
