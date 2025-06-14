package com.ciroiencom.gamingheaventfc.controller;

import com.ciroiencom.gamingheaventfc.model.Usuario;
import com.ciroiencom.gamingheaventfc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;

@Controller
@RequestMapping("")
public class FooterController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/about")
    public String getAbout(Model model, @AuthenticationPrincipal User userLogged) {

        if(userLogged != null) {
            Usuario user = usuarioService.findByNickname(userLogged.getUsername());
            model.addAttribute("imgUser", Base64.getEncoder().encodeToString(user.getImg()));
        }

        return "pages/about";
    }

    @GetMapping("/policies")
    public String getPolicies(Model model, @AuthenticationPrincipal User userLogged) {

        if(userLogged != null) {
            Usuario user = usuarioService.findByNickname(userLogged.getUsername());
            model.addAttribute("imgUser", Base64.getEncoder().encodeToString(user.getImg()));
        }

        return "pages/policies";
    }

}
