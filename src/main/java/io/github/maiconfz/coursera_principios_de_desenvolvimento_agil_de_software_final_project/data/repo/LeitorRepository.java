package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Estilo;
import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Leitor;

public interface LeitorRepository extends JpaRepository<Leitor, Long> {

    public Optional<Leitor> findByEmail(String email);

    public boolean existsByEmail(String email);

    public int countByLivrosLidosEstilo(Estilo estilo);

    public List<Leitor> findTop10ByOrderByPontuacaoLeituraDesc();
}
