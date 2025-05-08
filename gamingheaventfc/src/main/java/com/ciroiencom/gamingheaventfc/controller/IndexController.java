package com.ciroiencom.gamingheaventfc.controller;

import com.ciroiencom.gamingheaventfc.model.Juego;
import com.ciroiencom.gamingheaventfc.service.JuegoService;
import com.ciroiencom.gamingheaventfc.service.external.FreeToGameApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    JuegoService juegoService;

    @GetMapping("")
    public String getIndex(Model model) {
        ArrayList<Juego> videogames = juegoService.getAll();
        ArrayList<String> genres = juegoService.getAllGenresFromGames();

        model.addAttribute("videogames", videogames);
        model.addAttribute("genres", genres);
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