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
public class LeituraLivroController {
    private final LeitorRepository leitorRepository;
    private final LivroRepository livroRepository;

    @GetMapping("/livro/marcar-como-lido/{id}")
    @Transactional
    public String marcarComoLivro(@PathVariable("id") long id, Model model, Authentication authentication) {
        var leitorOpt = this.leitorRepository
                .findById(AuthenticationUtils.getAppUserDetails(authentication).getLeitor().getId());
        if (leitorOpt.isPresent()) {
            var leitor = leitorOpt.get();
            var livro = this.livroRepository.findById(id);
            if (livro.isPresent()) {
                leitor.getLivrosLidos().add(livro.get());
                this.leitorRepository.save(leitor);
            }
        }
        return "redirect:/livro/" + id;
    }

    @GetMapping("/livro/desmarcar-como-lido/{id}")
    @Transactional
    public String desmarcarComoLivro(@PathVariable("id") long id, Model model, Authentication authentication) {
        var leitorOpt = this.leitorRepository
                .findById(AuthenticationUtils.getAppUserDetails(authentication).getLeitor().getId());
        if (leitorOpt.isPresent()) {
            var leitor = leitorOpt.get();
            var livro = this.livroRepository.findById(id);
            if (livro.isPresent()) {
                leitor.getLivrosLidos().remove(livro.get());
                this.leitorRepository.save(leitor);
            }
        }
        return "redirect:/livro/" + id;
    }

}
