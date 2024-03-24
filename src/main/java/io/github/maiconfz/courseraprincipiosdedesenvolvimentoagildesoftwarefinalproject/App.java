package io.github.maiconfz.courseraprincipiosdedesenvolvimentoagildesoftwarefinalproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.maiconfz.courseraprincipiosdedesenvolvimentoagildesoftwarefinalproject.data.model.Estilo;
import io.github.maiconfz.courseraprincipiosdedesenvolvimentoagildesoftwarefinalproject.data.model.Trofeu;
import io.github.maiconfz.courseraprincipiosdedesenvolvimentoagildesoftwarefinalproject.data.repo.EstiloRepository;
import io.github.maiconfz.courseraprincipiosdedesenvolvimentoagildesoftwarefinalproject.data.repo.TrofeuRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner initData(EstiloRepository estiloRepository, TrofeuRepository trofeuRepository) {
		return args -> {
			estiloRepository.save(new Estilo("Romance"));
			estiloRepository.save(new Estilo("Ficção Científica"));
			estiloRepository.save(new Estilo("Terror"));
			estiloRepository.save(new Estilo("Fantasia"));
			estiloRepository.save(new Estilo("Biografia"));

			estiloRepository.findAll().forEach(
					estilo -> trofeuRepository.save(new Trofeu(String.format("Leitor de %s", estilo.getNome()))));

			

			log.debug("Estilos e troféus iniciais criados com sucesso.");
			log.debug("Estilos criados: {}", estiloRepository.count());
			log.debug("Troféus criados: {}", trofeuRepository.count());
		};
	}
}
