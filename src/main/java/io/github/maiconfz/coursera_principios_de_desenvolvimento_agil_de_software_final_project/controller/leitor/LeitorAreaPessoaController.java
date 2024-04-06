package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.controller.leitor;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Leitor;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo.LeitorRepository;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.security.AuthenticationUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LeitorAreaPessoaController {
    private final LeitorRepository leitorRepository;

    @GetMapping("/leitor/area-pessoal")
    @Transactional
    public String getLivro(Model model, Authentication authentication) {

        final Leitor leitor = leitorRepository
                .findById(AuthenticationUtils.getAppUserDetails(authentication).getLeitor().getId())
                .orElseThrow(IllegalStateException::new);

        model.addAttribute("leitor", leitor);

        return "leitor/area-pessoal";
    }

}
