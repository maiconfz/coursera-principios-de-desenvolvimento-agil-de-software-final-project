package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
