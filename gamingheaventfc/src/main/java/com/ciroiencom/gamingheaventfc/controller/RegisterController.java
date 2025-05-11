package com.ciroiencom.gamingheaventfc.controller;

import com.ciroiencom.gamingheaventfc.model.Rol;
import com.ciroiencom.gamingheaventfc.model.Usuario;
import com.ciroiencom.gamingheaventfc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("")
    public String getIndex(Model model) {
        return "pages/register";
    }

    @PostMapping("/saveUser")
    public String saveUser(@RequestParam String nickname,
                           @RequestParam String email,
                           @RequestParam String pass) {
        try {
            InputStream inStream = getClass().getResourceAsStream("/static/images/default_user.png");
            byte[] imgBytes = inStream.readAllBytes();

            Usuario usr = new Usuario(nickname, email, pass, Rol.USER, imgBytes);
            usuarioService.save(usr);
            return "redirect:/";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}