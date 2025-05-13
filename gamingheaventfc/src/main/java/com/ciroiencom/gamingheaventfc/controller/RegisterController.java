package com.ciroiencom.gamingheaventfc.controller;

import com.ciroiencom.gamingheaventfc.model.Rol;
import com.ciroiencom.gamingheaventfc.model.Usuario;
import com.ciroiencom.gamingheaventfc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("")
    public String getIndex(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "pages/register";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute Usuario user) {
        try {
            InputStream inStream = getClass().getResourceAsStream("/static/images/default_user.png");
            byte[] imgBytes = inStream.readAllBytes();

            //Usuario usr = new Usuario(nickname, email, pass, Rol.USER, imgBytes);
            user.setImg(imgBytes);
            user.setRol(Rol.USER);
            usuarioService.save(user);
            return "redirect:/";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}