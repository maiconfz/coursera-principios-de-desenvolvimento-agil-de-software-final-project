package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Leitor;

public interface LeitorRepository extends JpaRepository<Leitor, Long> {

    public Optional<Leitor> findByEmail(String email);
}
