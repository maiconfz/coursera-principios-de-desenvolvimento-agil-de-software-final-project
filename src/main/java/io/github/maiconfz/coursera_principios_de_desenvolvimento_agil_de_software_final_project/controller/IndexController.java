package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo.LivroRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class IndexController {

    private final LivroRepository livroRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("livros", livroRepository.findAll());
        return "index";
    }
}
