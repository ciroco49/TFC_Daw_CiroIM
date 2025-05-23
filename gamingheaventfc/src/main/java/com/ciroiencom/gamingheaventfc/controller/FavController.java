package com.ciroiencom.gamingheaventfc.controller;

import com.ciroiencom.gamingheaventfc.model.Juego;
import com.ciroiencom.gamingheaventfc.model.Usuario;
import com.ciroiencom.gamingheaventfc.service.JuegoService;
import com.ciroiencom.gamingheaventfc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RequestMapping("")
@RestController
public class FavController {

    @Autowired
    JuegoService juegoService;
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/fav/save/{videogameId}")
    public ResponseEntity<?> saveLikeInGame(@PathVariable Long videogameId, @AuthenticationPrincipal User userLogged) {

        if (userLogged == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


        Usuario usuarioBBDD = usuarioService.findByNicknameWithFavs(userLogged.getUsername());

        if(usuarioBBDD.getJuegosFav().size() >= 3) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }

        Juego juegoBBDD = juegoService.findById(videogameId).orElseThrow();

        Set<Juego> juegosFav = new HashSet<>(usuarioBBDD.getJuegosFav());

        juegosFav.add(juegoBBDD);
        usuarioBBDD.setJuegosFav(juegosFav);
        usuarioService.save(usuarioBBDD);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/fav/remove/{videogameId}")
    public ResponseEntity<?> removeLikeInGame(@PathVariable Long videogameId, @AuthenticationPrincipal User userLogged) {

        if (userLogged == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Usuario usuarioBBDD = usuarioService.findByNickname(userLogged.getUsername());
        Juego juegoBBDD = juegoService.findById(videogameId).orElseThrow();

        Set<Juego> juegosFav = new HashSet<>(usuarioBBDD.getJuegosFav());

        juegosFav.remove(juegoBBDD);
        usuarioBBDD.setJuegosFav(juegosFav);
        usuarioService.save(usuarioBBDD);

        return ResponseEntity.ok().build();
    }


}
