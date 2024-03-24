package io.github.maiconfz.courseraprincipiosdedesenvolvimentoagildesoftwarefinalproject.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.maiconfz.courseraprincipiosdedesenvolvimentoagildesoftwarefinalproject.data.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
