package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Estilo;
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
        String[] names = { "Emma", "Liam", "Olivia", "Noah", "Ava", "Isabella", "Sophia", "Mia", "Charlotte",
                "Amelia" };

        for (String name : names) {
            leitorRepository.save(Leitor.builder()
                    .nome(name)
                    .email(name.toLowerCase() + "@gmail.com")
                    .senha(passwordEncoder.encode(name.toLowerCase()))
                    .pontuacaoLeitura(0)
                    .build());
        }
    }

    private void initTrofeus(EstiloRepository estiloRepository, TrofeuRepository trofeuRepository) {
        estiloRepository.findAll()
                .forEach(estilo -> trofeuRepository.save(new Trofeu(String.format("Leitor de %s", estilo.getNome()))));
    }

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

    private void initLivros(EstiloRepository estiloRepository, LivroRepository livroRepository) {

        final var estiloClassico = estiloRepository.findByNome(Estilo.EstiloEnum.CLASSICO.getNome()).orElseThrow(IllegalStateException::new);
        final var estiloDistopico = estiloRepository.findByNome(Estilo.EstiloEnum.DISTOPICO.getNome()).orElseThrow(IllegalStateException::new);
        final var estiloRomance = estiloRepository.findByNome(Estilo.EstiloEnum.ROMANCE.getNome()).orElseThrow(IllegalStateException::new);
        final var estiloModernista = estiloRepository.findByNome(Estilo.EstiloEnum.MODERNISTA.getNome()).orElseThrow(IllegalStateException::new);
        final var estiloAventura = estiloRepository.findByNome(Estilo.EstiloEnum.AVENTURA.getNome()).orElseThrow(IllegalStateException::new);
        final var estiloFantasia = estiloRepository.findByNome(Estilo.EstiloEnum.FANTASIA.getNome()).orElseThrow(IllegalStateException::new);
        final var estiloFiccaoHistorica = estiloRepository.findByNome(Estilo.EstiloEnum.FICCAO_HISTORICA.getNome()).orElseThrow(IllegalStateException::new);
        final var estiloSuspensePsicologico = estiloRepository.findByNome(Estilo.EstiloEnum.SUSPENSE_PSICOLOGICO.getNome()).orElseThrow(IllegalStateException::new);

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
                .descricao("Um romance histórico que retrata a vida de várias famílias aristocráticas russas durante o período das guerras napoleônicas.")
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
        
        livroRepository.save(Livro.builder()
                .titulo("Harry Potter e a Pedra Filosofal")
                .autor("J.K. Rowling")
                .ano(1997)
                .numeroPaginas(223)
                .estilo(estiloFantasia)
                .descricao("O primeiro livro da série Harry Potter, que conta a história de um jovem bruxo e suas aventuras na escola de magia e bruxaria de Hogwarts.")
                .build());

        livroRepository.save(Livro.builder()
                .titulo("As Crônicas de Nárnia: O Leão, a Feiticeira e o Guarda-Roupa")
                .autor("C.S. Lewis")
                .ano(1950)
                .numeroPaginas(206)
                .estilo(estiloFantasia)
                .descricao("O primeiro livro da série As Crônicas de Nárnia, que transporta os leitores para um mundo mágico cheio de criaturas fantásticas e aventuras emocionantes.")
                .build());

        livroRepository.save(Livro.builder()
                .titulo("Percy Jackson e o Ladrão de Raios")
                .autor("Rick Riordan")
                .ano(2005)
                .numeroPaginas(375)
                .estilo(estiloFantasia)
                .descricao("O primeiro livro da série Percy Jackson e os Olimpianos, que segue as aventuras de um jovem semideus e seus amigos enquanto lutam contra criaturas mitológicas e descobrem segredos antigos.")
                .build());

        livroRepository.save(Livro.builder()
                .titulo("O Nome do Vento")
                .autor("Patrick Rothfuss")
                .ano(2007)
                .numeroPaginas(662)
                .estilo(estiloFantasia)
                .descricao("O primeiro livro da trilogia Crônica do Matador do Rei, que narra a história de Kvothe, um músico e mago talentoso, enquanto ele busca vingança e desvenda os mistérios do mundo.")
                .build());

        livroRepository.save(Livro.builder()
                .titulo("A Guerra dos Tronos")
                .autor("George R.R. Martin")
                .ano(1996)
                .numeroPaginas(694)
                .estilo(estiloFantasia)
                .descricao("O primeiro livro da série As Crônicas de Gelo e Fogo, que apresenta um mundo medieval repleto de intriga política, batalhas épicas e criaturas míticas.")
                .build());

        livroRepository.save(Livro.builder()
                .titulo("A Ilha do Tesouro")
                .autor("Robert Louis Stevenson")
                .ano(1883)
                .numeroPaginas(366)
                .estilo(estiloAventura)
                .descricao("Um clássico da literatura de aventura que segue as peripécias do jovem Jim Hawkins em busca de um tesouro enterrado em uma ilha remota.")
                .build());

        livroRepository.save(Livro.builder()
                .titulo("Viagem ao Centro da Terra")
                .autor("Jules Verne")
                .ano(1864)
                .numeroPaginas(183)
                .estilo(estiloAventura)
                .descricao("Uma emocionante história de aventura que acompanha uma expedição científica que desce ao centro da Terra através de uma cratera vulcânica.")
                .build());

        livroRepository.save(Livro.builder()
                .titulo("A Volta ao Mundo em 80 Dias")
                .autor("Jules Verne")
                .ano(1873)
                .numeroPaginas(256)
                .estilo(estiloAventura)
                .descricao("Um clássico da literatura de viagem que segue as aventuras de Phileas Fogg enquanto ele tenta dar a volta ao mundo em 80 dias para ganhar uma aposta.")
                .build());

        livroRepository.save(Livro.builder()
                .titulo("Aventuras de Tom Sawyer")
                .autor("Mark Twain")
                .ano(1876)
                .numeroPaginas(224)
                .estilo(estiloAventura)
                .descricao("Um livro que retrata as travessuras e aventuras do jovem Tom Sawyer em uma pequena cidade do interior dos Estados Unidos.")
                .build());

        livroRepository.save(Livro.builder()
                .titulo("Aventuras de Huckleberry Finn")
                .autor("Mark Twain")
                .ano(1884)
                .numeroPaginas(366)
                .estilo(estiloAventura)
                .descricao("A continuação das aventuras de Tom Sawyer, que segue as peripécias do jovem Huckleberry Finn enquanto ele foge de casa e embarca em uma jornada pelo rio Mississippi.")
                .build());
        
        
    }
}
