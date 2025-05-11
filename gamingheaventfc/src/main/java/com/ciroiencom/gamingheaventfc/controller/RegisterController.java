package com.ciroiencom.gamingheaventfc.controller;

import com.ciroiencom.gamingheaventfc.model.Juego;
import com.ciroiencom.gamingheaventfc.service.JuegoService;
import com.ciroiencom.gamingheaventfc.service.external.FreeToGameApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    JuegoService juegoService;

    @GetMapping("")
    public String getIndex(Model model) {
        return "pages/register";
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