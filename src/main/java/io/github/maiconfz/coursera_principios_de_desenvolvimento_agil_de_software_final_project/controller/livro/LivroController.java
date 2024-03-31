package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.controller.livro;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo.LeitorRepository;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo.LivroRepository;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.security.AuthenticationUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LivroController {
    private final LeitorRepository leitorRepository;
    private final LivroRepository livroRepository;

    @GetMapping("/livro/{id}")
    @Transactional
    public String getLivro(@PathVariable("id") long id, Model model, Authentication authentication) {
        var livroOpt = livroRepository.findById(id);

        if (livroOpt.isPresent()) {
            var livro = livroOpt.get();
            var leitor = leitorRepository
                    .findById(AuthenticationUtils.getAppUserDetails(authentication).getLeitor().getId()).get();

            livro.setLido(leitor.getLivrosLidos().contains(livro));
            model.addAttribute("livro", livro);
            return "livro";
        } else {
            return "redirect:/";
        }
    }

}
