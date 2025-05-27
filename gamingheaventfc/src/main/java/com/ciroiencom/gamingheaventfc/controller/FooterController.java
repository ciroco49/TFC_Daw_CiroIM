package com.ciroiencom.gamingheaventfc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class FooterController {

    @GetMapping("/about")
    public String getAbout() {
        return "pages/about";
    }

    @GetMapping("/policies")
    public String getPolicies() {
        return "pages/policies";
    }

}
