package com.ciroiencom.gamingheaventfc.controller;

import com.ciroiencom.gamingheaventfc.model.Usuario;
import com.ciroiencom.gamingheaventfc.model.ValidationGroups;
import com.ciroiencom.gamingheaventfc.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("")
    public String getIndex(Model model) {
        //Si no recibe una redirección con un usuario con errores vinculados, creo uno vacío
        if(!model.containsAttribute("usuario")) {
            model.addAttribute("usuario", new Usuario());
        }
        return "pages/login";
    }

    @PostMapping("/loginUser")
    public String loginUser(@Validated(ValidationGroups.Login.class) @ModelAttribute("usuario") Usuario user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.usuario", bindingResult);
            redirectAttributes.addFlashAttribute("usuario", user);
            return "redirect:/login";
        }

        //TODO - PROCESO PARA LOGIN AQUÍ
        return "redirect:/";
    }

}