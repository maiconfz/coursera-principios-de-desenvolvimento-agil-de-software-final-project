package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.controller.livro;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Trofeu;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo.LeitorRepository;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo.LivroRepository;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo.TrofeuRepository;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.security.AuthenticationUtils;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.utils.LeituraLivroUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class LeituraLivroController {
    private final LeitorRepository leitorRepository;
    private final LivroRepository livroRepository;
    private final TrofeuRepository trofeuRepository;

    @GetMapping("/livro/marcar-como-lido/{id}")
    @Transactional
    public String marcarComoLivro(@PathVariable("id") long id, Model model, Authentication authentication) {
        var leitorAutenticado = AuthenticationUtils.getAppUserDetails(authentication).getLeitor();
        var leitorOpt = this.leitorRepository
                .findById(AuthenticationUtils.getAppUserDetails(authentication).getLeitor().getId());
        if (leitorOpt.isPresent()) {
            var leitor = leitorOpt.get();
            var livro = this.livroRepository.findById(id);
            if (livro.isPresent()) {

                leitor.getLivrosLidos().add(livro.get());
                leitor.setPontuacaoLeitura(
                        leitor.getPontuacaoLeitura() + LeituraLivroUtils.calcularPontuacaoLeitura(livro.get()));
                leitorAutenticado.setPontuacaoLeitura(leitor.getPontuacaoLeitura());

                this.leitorRepository.save(leitor);

                if (leitorRepository.countByLivrosLidosEstilo(livro.get().getEstilo()) >= 5) {
                    final Trofeu trofeu = this.trofeuRepository.findByEstilo(livro.get().getEstilo());

                    log.info("Leitor {} ganhou o troféu {}", leitor.getNome(), trofeu.getNome());

                    leitor.getTrofeusConquistados().add(this.trofeuRepository.findByEstilo(livro.get().getEstilo()));
                    this.leitorRepository.save(leitor);
                }
            }
        }
        return "redirect:/livro/" + id;
    }

    @GetMapping("/livro/desmarcar-como-lido/{id}")
    @Transactional
    public String desmarcarComoLivro(@PathVariable("id") long id, Model model, Authentication authentication) {
        var leitorAutenticado = AuthenticationUtils.getAppUserDetails(authentication).getLeitor();
        var leitorOpt = this.leitorRepository
                .findById(AuthenticationUtils.getAppUserDetails(authentication).getLeitor().getId());
        if (leitorOpt.isPresent()) {
            var leitor = leitorOpt.get();
            var livro = this.livroRepository.findById(id);
            if (livro.isPresent()) {
                leitor.getLivrosLidos().remove(livro.get());
                leitor.setPontuacaoLeitura(
                        leitor.getPontuacaoLeitura() - LeituraLivroUtils.calcularPontuacaoLeitura(livro.get()));
                leitorAutenticado.setPontuacaoLeitura(leitor.getPontuacaoLeitura());
                this.leitorRepository.save(leitor);

                if (leitorRepository.countByLivrosLidosEstilo(livro.get().getEstilo()) < 5) {
                    final Trofeu trofeu = this.trofeuRepository.findByEstilo(livro.get().getEstilo());

                    log.info("Leitor {} perdeu o troféu {}", leitor.getNome(), trofeu.getNome());

                    leitor.getTrofeusConquistados().remove(this.trofeuRepository.findByEstilo(livro.get().getEstilo()));
                    this.leitorRepository.save(leitor);
                }

            }
        }
        return "redirect:/livro/" + id;
    }

}
