package com.ciroiencom.gamingheaventfc.controller;

import com.ciroiencom.gamingheaventfc.model.Usuario;
import com.ciroiencom.gamingheaventfc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/social")
public class SocialController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("")
    public String getSocial(Model model, @AuthenticationPrincipal User userLogged) {

        List<Usuario> users = usuarioService.findAll();

        Map<Usuario, String> usersMapWithImg = new LinkedHashMap<>();

        for (Usuario user : users) {
            if (user.getImg() != null)
                usersMapWithImg.put(user, Base64.getEncoder().encodeToString(user.getImg()));
        }

        Usuario usuario = usuarioService.findByNickname(userLogged.getUsername());

        model.addAttribute("usersMapWithImg", usersMapWithImg);
        model.addAttribute("imgUser", Base64.getEncoder().encodeToString(usuario.getImg()));

        return "pages/social";
    }

    @GetMapping("/{idUser}")
    public String getUserInfo(@PathVariable Long idUser, Model model, @AuthenticationPrincipal User userLogged) {
        Usuario userSelected = usuarioService.findById(idUser).orElseThrow();
        Usuario userLoggedBBDD = usuarioService.findByNickname(userLogged.getUsername());

        model.addAttribute("user", userSelected);
        model.addAttribute("imgUserSelected", Base64.getEncoder().encodeToString(userSelected.getImg()));
        model.addAttribute("imgUser", Base64.getEncoder().encodeToString(userLoggedBBDD.getImg()));
        model.addAttribute("juegosFav", userSelected.getJuegosFav());

        return "pages/socialUserInfo";
    }

}