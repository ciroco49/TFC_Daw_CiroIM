package com.ciroiencom.gamingheaventfc.controller;

import com.ciroiencom.gamingheaventfc.model.Juego;
import com.ciroiencom.gamingheaventfc.repository.JuegoRepository;
import com.ciroiencom.gamingheaventfc.service.JuegoService;
import com.ciroiencom.gamingheaventfc.service.external.FreeToGameApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    JuegoService juegoService;

    @GetMapping("")
    public String getIndex(Model model) {

        //ESTO LO EJECUTARÉ CON UN BTN EN LA APP
        /* ArrayList<Juego> videogames = FreeToGameApiClient.getJuegos();

        for(Juego videogame: videogames) {
            try {
                juegoService.saveOrUpdate(videogame);
            } catch(Exception ex) {
                System.err.println(ex);
            }
        } */

        ArrayList<Juego> videogames = juegoService.getAll();

        model.addAttribute("videogames", videogames);
        return "pages/index";
    }
}