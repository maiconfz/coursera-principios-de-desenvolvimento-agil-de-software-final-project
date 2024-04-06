package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Estilo;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Estilo.EstiloEnum;

public interface EstiloRepository extends JpaRepository<Estilo, Long> {

    Optional<Estilo> findByChave(EstiloEnum chave);

    Optional<Estilo> findByNome(String nome);

}
