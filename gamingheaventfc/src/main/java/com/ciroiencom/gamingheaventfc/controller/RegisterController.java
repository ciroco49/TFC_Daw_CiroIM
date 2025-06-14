package com.ciroiencom.gamingheaventfc.controller;

import com.ciroiencom.gamingheaventfc.model.Rol;
import com.ciroiencom.gamingheaventfc.model.Usuario;
import com.ciroiencom.gamingheaventfc.model.ValidationGroups;
import com.ciroiencom.gamingheaventfc.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String getIndex(Model model) {
        //Si no recibe una redirección con un usuario con errores vinculados, creo uno vacío
        if(!model.containsAttribute("usuario"))
            model.addAttribute("usuario", new Usuario());

        if(model.containsAttribute("nicknameExists"))
            model.addAttribute("nicknameExists", model.getAttribute("nicknameExists"));

        if(model.containsAttribute("correoExists"))
            model.addAttribute("correoExists", model.getAttribute("correoExists"));

        return "pages/register";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Validated(ValidationGroups.Register.class) @ModelAttribute("usuario") Usuario user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.usuario", bindingResult);
            redirectAttributes.addFlashAttribute("usuario", user);
            return "redirect:/register";
        }

        if(usuarioService.findByNickname(user.getNickname()) != null) {
            redirectAttributes.addFlashAttribute("nicknameExists", "This nickname is not available.");
            return "redirect:/register";
        }

        if(usuarioService.findByCorreo(user.getCorreo()) != null) {
            redirectAttributes.addFlashAttribute("correoExists", "This email is not available.");
            return "redirect:/register";
        }

        try {
            InputStream inStream = getClass().getResourceAsStream("/static/images/default_user.png");
            byte[] imgBytes = inStream.readAllBytes();

            user.setImg(imgBytes);
            user.setRol(Rol.USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            usuarioService.save(user);
            return "redirect:/login";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}