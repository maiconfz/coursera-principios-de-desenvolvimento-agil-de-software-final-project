package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model;

import java.io.Serializable;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder(toBuilder = true)
@Entity
public class Estilo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private EstiloEnum chave;

    @Column(nullable = false, unique = true)
    private String nome;

    public Estilo(String nome) {
        this.nome = nome;
    }

    public static Estilo of(EstiloEnum estiloEnum) {
        return builder().chave(estiloEnum).nome(estiloEnum.getNome()).build();
    }

    @AllArgsConstructor
    @Getter
    public enum EstiloEnum {
        AVENTURA("Aventura"),
        CLASSICO("Clássico"),
        DISTOPICO("Distópico"),
        FANTASIA("Fantasia"),
        FICCAO_HISTORICA("Ficção Histórica"),
        MODERNISTA("Modernista"),
        ROMANCE("Romance"),
        SUSPENSE_PSICOLOGICO("Suspense Psicológico");

        private final String nome;

        public static Optional<EstiloEnum> of(String nome) {
            for (EstiloEnum estilo : EstiloEnum.values()) {
                if (estilo.nome.equalsIgnoreCase(nome)) {
                    return Optional.of(estilo);
                }
            }
            return Optional.empty();
        }
    }
}
