package com.ciroiencom.gamingheaventfc.controller;

import com.ciroiencom.gamingheaventfc.model.Juego;
import com.ciroiencom.gamingheaventfc.model.Usuario;
import com.ciroiencom.gamingheaventfc.service.JuegoService;
import com.ciroiencom.gamingheaventfc.service.UsuarioService;
import com.ciroiencom.gamingheaventfc.service.external.FreeToGameApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    JuegoService juegoService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("")
    public String getIndex(Model model, @AuthenticationPrincipal User userLogged) {
        ArrayList<Juego> videogames = juegoService.getAll();
        ArrayList<String> genres = juegoService.getAllGenresFromGames();

        model.addAttribute("videogames", videogames);
        model.addAttribute("genres", genres);

        if(userLogged == null) {
            return "pages/index";
        }

        Usuario usuario = usuarioService.findByNickname(userLogged.getUsername());
        model.addAttribute("user", usuario);
        model.addAttribute("imgUser", Base64.getEncoder().encodeToString(usuario.getImg()));

        return "pages/index";
    }

    @GetMapping("/reloadBBDD")
    public String reloadBBDDWithApi() {
        ArrayList<Juego> videogames = FreeToGameApiClient.getJuegos();

        for(Juego videogame: videogames) {
            try {
                juegoService.saveOrUpdate(videogame);
            } catch(Exception ex) {
                System.err.println(ex);
            }
        }
        return "redirect:/";
    }

}