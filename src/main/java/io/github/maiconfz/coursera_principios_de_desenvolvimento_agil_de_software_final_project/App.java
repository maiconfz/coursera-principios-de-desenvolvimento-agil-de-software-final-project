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
				.titulo("O Sol é para Todos")
				.autor("Harper Lee")
				.ano(1960)
				.numeroPaginas(281)
				.estilo(estiloClassico)
				.descricao("Um clássico da literatura que aborda temas como racismo e injustiça social.")
				.build());

		livroRepository.save(Livro.builder()
				.titulo("1984")
				.autor("George Orwell")
				.ano(1949)
				.numeroPaginas(328)
				.estilo(estiloDistopico)
				.descricao("Uma distopia clássica que retrata um futuro sombrio e opressivo, onde a liberdade individual é suprimida.")
				.build());

		livroRepository.save(Livro.builder()
				.titulo("O Grande Gatsby")
				.autor("F. Scott Fitzgerald")
				.ano(1925)
				.numeroPaginas(180)
				.estilo(estiloClassico)
				.descricao("Um clássico da literatura americana que retrata a decadência da sociedade durante a era do jazz.")
				.build());

		livroRepository.save(Livro.builder()
				.titulo("Orgulho e Preconceito")
				.autor("Jane Austen")
				.ano(1813)
				.numeroPaginas(279)
				.estilo(estiloRomance)
				.descricao("Um clássico da literatura inglesa que retrata a sociedade do século XIX, abordando temas como amor, casamento e preconceito.")
				.build());

		livroRepository.save(Livro.builder()
				.titulo("Ao Farol")
				.autor("Virginia Woolf")
				.ano(1927)
				.numeroPaginas(209)
				.estilo(estiloModernista)
				.descricao("Um romance modernista que explora a complexidade da mente humana e as mudanças sociais do início do século XX.")
				.build());

		livroRepository.save(Livro.builder()
				.titulo("Moby-Dick")
				.autor("Herman Melville")
				.ano(1851)
				.numeroPaginas(585)
				.estilo(estiloAventura)
				.descricao("Um clássico da literatura que narra a história do capitão Ahab em busca da baleia branca.")
				.build());

		livroRepository.save(Livro.builder()
				.titulo("O Senhor dos Anéis")
				.autor("J.R.R. Tolkien")
				.ano(1954)
				.numeroPaginas(1178)
				.estilo(estiloFantasia)
				.descricao("Uma épica história de fantasia que se passa na Terra Média, onde um pequeno hobbit chamado Frodo embarca em uma jornada perigosa para destruir um poderoso anel.")
				.build());

		livroRepository.save(Livro.builder()
				.titulo("Guerra e Paz")
				.autor("Leo Tolstoy")
				.ano(1869)
				.numeroPaginas(1225)
				.estilo(estiloFiccaoHistorica)
				.descricao(
						"Um romance histórico que retrata a vida de várias famílias aristocráticas russas durante o período das guerras napoleônicas.")
				.build());

		livroRepository.save(Livro.builder()
				.titulo("Crime e Castigo")
				.autor("Fyodor Dostoevsky")
				.ano(1866)
				.numeroPaginas(671)
				.estilo(estiloSuspensePsicologico)
				.descricao("Um clássico da literatura russa que explora a mente de um jovem estudante que comete um assassinato e lida com as consequências psicológicas de seus atos.")
				.build());

		livroRepository.save(Livro.builder()
				.titulo("O Apanhador no Campo de Centeio")
				.autor("J.D. Salinger")
				.ano(1951)
				.numeroPaginas(277)
				.estilo(estiloClassico)
				.descricao("Um romance que retrata a jornada de um adolescente rebelde chamado Holden Caulfield, enquanto ele lida com a alienação e a busca por significado na sociedade.")
				.build());

		livroRepository.save(Livro.builder()
				.titulo("O Hobbit")
				.autor("J.R.R. Tolkien")
				.ano(1937)
				.numeroPaginas(310)
				.estilo(estiloFantasia)
				.descricao("Uma aventura encantadora que segue Bilbo Bolseiro, um hobbit relutante, em sua jornada para ajudar um grupo de anões a recuperar seu tesouro roubado por um dragão.")
				.build());

		livroRepository.save(Livro.builder()
				.titulo("O Alquimista")
				.autor("Paulo Coelho")
				.ano(1988)
				.numeroPaginas(197)
				.estilo(estiloRomance)
				.descricao("Uma história inspiradora sobre um jovem pastor que embarca em uma jornada em busca de seu tesouro pessoal e descobre lições valiosas sobre o significado da vida.")
				.build());
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
