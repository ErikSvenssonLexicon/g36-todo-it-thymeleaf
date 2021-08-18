package se.lexicon.g36todoit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {

    @GetMapping(value = {"", "/", "/index"})
    public String loadWelcome(){
        return "index";
    }

    @GetMapping("/public/login")
    public String getLoginPage(){
        return "login";
    }

}
