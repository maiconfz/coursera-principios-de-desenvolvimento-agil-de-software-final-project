package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Estilo;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Leitor;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Livro;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Trofeu;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo.EstiloRepository;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo.LeitorRepository;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo.LivroRepository;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo.TrofeuRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner initData(EstiloRepository estiloRepository, TrofeuRepository trofeuRepository,
			LeitorRepository leitorRepository, LivroRepository livroRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		return args -> {
			initEstilos(estiloRepository);
			initTrofeus(estiloRepository, trofeuRepository);
			initLeitores(leitorRepository, bCryptPasswordEncoder);
			initLivros(estiloRepository, livroRepository);

			log.debug("Estilos e troféus iniciais criados com sucesso.");
			log.debug("Estilos criados: {}", estiloRepository.count());
			log.debug("Troféus criados: {}", trofeuRepository.count());
			log.debug("Leitores criados: {}", leitorRepository.count());
			log.debug("Livros criados: {}", livroRepository.count());
		};
	}

	@SuppressWarnings("null")
	private void initLeitores(LeitorRepository leitorRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		String[] names = { "Emma", "Liam", "Olivia", "Noah", "Ava", "Isabella", "Sophia", "Mia", "Charlotte",
				"Amelia" };

		for (String name : names) {
			leitorRepository.save(Leitor.builder()
					.nome(name)
					.email(name.toLowerCase() + "@gmail.com")
					.senha(bCryptPasswordEncoder.encode(name.toLowerCase()))
					.pontos(0)
					.build());
		}
	}

	private void initTrofeus(EstiloRepository estiloRepository, TrofeuRepository trofeuRepository) {
		estiloRepository.findAll()
				.forEach(estilo -> trofeuRepository.save(new Trofeu(String.format("Leitor de %s", estilo.getNome()))));
	}

	@SuppressWarnings("null")
	private void initEstilos(EstiloRepository estiloRepository) {
		estiloRepository.save(Estilo.of(Estilo.EstiloEnum.AVENTURA));
		estiloRepository.save(Estilo.of(Estilo.EstiloEnum.CLASSICO));
		estiloRepository.save(Estilo.of(Estilo.EstiloEnum.DISTOPICO));
		estiloRepository.save(Estilo.of(Estilo.EstiloEnum.FANTASIA));
		estiloRepository.save(Estilo.of(Estilo.EstiloEnum.FICCAO_HISTORICA));
		estiloRepository.save(Estilo.of(Estilo.EstiloEnum.MODERNISTA));
		estiloRepository.save(Estilo.of(Estilo.EstiloEnum.ROMANCE));
		estiloRepository.save(Estilo.of(Estilo.EstiloEnum.SUSPENSE_PSICOLOGICO));
	}

	@SuppressWarnings("null")
	private void initLivros(EstiloRepository estiloRepository, LivroRepository livroRepository) {

		final var estiloClassico = estiloRepository.findByNome(Estilo.EstiloEnum.CLASSICO.getNome()).get();
		final var estiloDistopico = estiloRepository.findByNome(Estilo.EstiloEnum.DISTOPICO.getNome()).get();
		final var estiloRomance = estiloRepository.findByNome(Estilo.EstiloEnum.ROMANCE.getNome()).get();
		final var estiloModernista = estiloRepository.findByNome(Estilo.EstiloEnum.MODERNISTA.getNome()).get();
		final var estiloAventura = estiloRepository.findByNome(Estilo.EstiloEnum.AVENTURA.getNome()).get();
		final var estiloFantasia = estiloRepository.findByNome(Estilo.EstiloEnum.FANTASIA.getNome()).get();
		final var estiloFiccaoHistorica = estiloRepository.findByNome(Estilo.EstiloEnum.FICCAO_HISTORICA.getNome())
				.get();
		final var estiloSuspensePsicologico = estiloRepository
				.findByNome(Estilo.EstiloEnum.SUSPENSE_PSICOLOGICO.getNome()).get();

		livroRepository.save(Livro.builder()
				.titulo("To Kill a Mockingbird")
				.autor("Harper Lee")
				.anoPublicacao(1960)
				.numeroPaginas(281)
				.estilo(estiloClassico)
				.build());

		livroRepository.save(Livro.builder()
				.titulo("1984")
				.autor("George Orwell")
				.anoPublicacao(1949)
				.numeroPaginas(328)
				.estilo(estiloDistopico)
				.build());

		livroRepository.save(Livro.builder()
				.titulo("The Great Gatsby")
				.autor("F. Scott Fitzgerald")
				.anoPublicacao(1925)
				.numeroPaginas(180)
				.estilo(estiloClassico)
				.build());

		livroRepository.save(Livro.builder()
				.titulo("Pride and Prejudice")
				.autor("Jane Austen")
				.anoPublicacao(1813)
				.numeroPaginas(279)
				.estilo(estiloRomance)
				.build());

		livroRepository.save(Livro.builder()
				.titulo("To the Lighthouse")
				.autor("Virginia Woolf")
				.anoPublicacao(1927)
				.numeroPaginas(209)
				.estilo(estiloModernista)
				.build());

		livroRepository.save(Livro.builder()
				.titulo("Moby-Dick")
				.autor("Herman Melville")
				.anoPublicacao(1851)
				.numeroPaginas(585)
				.estilo(estiloAventura)
				.build());

		livroRepository.save(Livro.builder()
				.titulo("The Lord of the Rings")
				.autor("J.R.R. Tolkien")
				.anoPublicacao(1954)
				.numeroPaginas(1178)
				.estilo(estiloFantasia)
				.build());

		livroRepository.save(Livro.builder()
				.titulo("War and Peace")
				.autor("Leo Tolstoy")
				.anoPublicacao(1869)
				.numeroPaginas(1225)
				.estilo(estiloFiccaoHistorica)
				.build());

		livroRepository.save(Livro.builder()
				.titulo("Crime and Punishment")
				.autor("Fyodor Dostoevsky")
				.anoPublicacao(1866)
				.numeroPaginas(671)
				.estilo(estiloSuspensePsicologico)
				.build());

		livroRepository.save(Livro.builder()
				.titulo("The Catcher in the Rye")
				.autor("J.D. Salinger")
				.anoPublicacao(1951)
				.numeroPaginas(277)
				.estilo(estiloClassico)
				.build());

		livroRepository.save(Livro.builder()
				.titulo("The Hobbit")
				.autor("J.R.R. Tolkien")
				.anoPublicacao(1937)
				.numeroPaginas(310)
				.estilo(estiloFantasia)
				.build());

		livroRepository.save(Livro.builder()
				.titulo("The Alchemist")
				.autor("Paulo Coelho")
				.anoPublicacao(1988)
				.numeroPaginas(197)
				.estilo(estiloRomance)
				.build());
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
