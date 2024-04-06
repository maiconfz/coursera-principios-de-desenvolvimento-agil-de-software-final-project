package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.init;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Estilo;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Estilo.EstiloEnum;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Leitor;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Livro;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Trofeu;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo.EstiloRepository;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo.LeitorRepository;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo.LivroRepository;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo.TrofeuRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DbInit {

        @Bean
        public CommandLineRunner initData(EstiloRepository estiloRepository, TrofeuRepository trofeuRepository,
                        LeitorRepository leitorRepository, LivroRepository livroRepository,
                        PasswordEncoder passwordEncoder) {
                return args -> {
                        initEstilos(estiloRepository);
                        initTrofeus(estiloRepository, trofeuRepository);
                        initLeitores(leitorRepository, passwordEncoder);
                        initLivros(estiloRepository, livroRepository);

                        log.debug("Estilos e troféus iniciais criados com sucesso.");
                        log.debug("Estilos criados: {}", estiloRepository.count());
                        log.debug("Troféus criados: {}", trofeuRepository.count());
                        log.debug("Leitores criados: {}", leitorRepository.count());
                        log.debug("Livros criados: {}", livroRepository.count());
                };
        }

        private void initLeitores(LeitorRepository leitorRepository, PasswordEncoder passwordEncoder) {
                List.of("Emma", "Liam", "Olivia", "Noah", "Ava", "Isabella", "Sophia", "Mia", "Charlotte", "Amelia")
                                .forEach(name -> {
                                        if (!leitorRepository.existsByEmail(name.toLowerCase() + "@gmail.com")) {
                                                leitorRepository.save(Leitor.builder()
                                                                .nome(name)
                                                                .email(name.toLowerCase() + "@gmail.com")
                                                                .senha(passwordEncoder.encode(name.toLowerCase()))
                                                                .pontuacaoLeitura(0)
                                                                .build());
                                        }
                                });
        }

        private void initTrofeus(EstiloRepository estiloRepository, TrofeuRepository trofeuRepository) {
                estiloRepository.findAll().forEach(estilo -> trofeuRepository
                                .save(new Trofeu(String.format("Leitor de %s", estilo.getNome()))));
        }

        private void initEstilos(EstiloRepository estiloRepository) {
                Arrays.stream(Estilo.EstiloEnum.values()).map(Estilo::of).forEach(estiloRepository::save);
        }

        private void initLivros(EstiloRepository estiloRepository, LivroRepository livroRepository) {

                final List<Livro> livros = new ArrayList<>();

                try (FileReader reader = new FileReader(new ClassPathResource("livros.json").getFile())) {
                        Gson gson = new Gson();
                        Type livroListType = new TypeToken<List<Livro>>() {
                        }.getType();
                        livros.addAll(gson.fromJson(reader, livroListType));
                } catch (IOException e) {
                        throw new IllegalStateException("Erro ao ler arquivo de livros.", e);
                }

                final Map<EstiloEnum, Estilo> estilos = livros.stream()
                                .map(livro -> livro.getEstilo().getChave())
                                .distinct()
                                .collect(Collectors.toMap(Function.identity(),
                                                estiloEnum -> estiloRepository.findByChave(estiloEnum)
                                                                .orElseThrow(IllegalStateException::new)));

                livros.forEach(livro -> {
                        livro.setEstilo(estilos.get(livro.getEstilo().getChave()));
                        livroRepository.save(livro);
                });
        }
}
