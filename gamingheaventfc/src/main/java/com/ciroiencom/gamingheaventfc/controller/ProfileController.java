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
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("")
    public String getProfile(Model model, @AuthenticationPrincipal User userLogged) {
        Usuario user = usuarioService.findByNickname(userLogged.getUsername());
        
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("email", user.getCorreo());
        model.addAttribute("description", user.getDescripcion());

        Usuario usuario = usuarioService.findByNickname(userLogged.getUsername());
        model.addAttribute("imgUser", Base64.getEncoder().encodeToString(usuario.getImg()));

        return "pages/profile";
    }

}
