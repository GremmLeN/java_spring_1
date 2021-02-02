package ru.geekbrains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WelcomeController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Добро пожаловать!");
        return "index";
    }

    @RequestMapping(value = "/redirectToProducts", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:/";
    }

}
