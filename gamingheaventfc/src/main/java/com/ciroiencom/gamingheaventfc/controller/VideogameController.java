package com.ciroiencom.gamingheaventfc.controller;

import com.ciroiencom.gamingheaventfc.model.Comentarios;
import com.ciroiencom.gamingheaventfc.model.Juego;
import com.ciroiencom.gamingheaventfc.model.Usuario;
import com.ciroiencom.gamingheaventfc.service.ComentariosService;
import com.ciroiencom.gamingheaventfc.service.JuegoService;
import com.ciroiencom.gamingheaventfc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/")
public class VideogameController {

    @Autowired
    JuegoService juegoService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ComentariosService comentariosService;

    @GetMapping("/{title}")
    public String getVideogameView(@PathVariable String title, Model model, @AuthenticationPrincipal User userLogged) {
        Juego juego = juegoService.findByTitle(title);
        List<Comentarios> comments = comentariosService.findAll();

        model.addAttribute("videogame", juego);
        model.addAttribute("comments", comments);

        if(userLogged == null) {
            return "pages/videogame";
        }

        Usuario user = usuarioService.findByNickname(userLogged.getUsername());
        model.addAttribute("imgUser", Base64.getEncoder().encodeToString(user.getImg()));

        return "pages/videogame";
    }

    @PostMapping("/{title}/comment")
    public String saveComment(@RequestParam String texto, @PathVariable String title, @AuthenticationPrincipal User userLogged) {
        Usuario user = usuarioService.findByNickname(userLogged.getUsername());
        Juego videogame = juegoService.findByTitle(title);

        Comentarios comment = new Comentarios(user, videogame, texto);
        comentariosService.save(comment);
        return "redirect:/" + title;
    }

}
