package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
public class Leitor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @ManyToMany
    @JoinTable(name = "leitor_livros_lidos")
    private List<Livro> livrosLidos;
    private long pontuacaoLeitura;
    @ManyToMany
    @JoinTable(name = "leitor_trofeu")
    private List<Trofeu> trofeusConquistados;

    public boolean isNew() {
        return this.id == null;
    }
}