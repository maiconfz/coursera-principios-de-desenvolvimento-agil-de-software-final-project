package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }
}
