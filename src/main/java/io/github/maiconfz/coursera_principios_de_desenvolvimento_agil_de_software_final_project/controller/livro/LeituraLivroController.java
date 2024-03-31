package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.controller.livro;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo.LivroRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LeituraLivroController {
    private final LivroRepository livroRepository;

    @GetMapping("/livro/marcar-como-lido/{id}")
    public String marcarComoLivro(@PathVariable("id") long id, Model model) {
        return "redirect:/livro/" + id;
    }

}
