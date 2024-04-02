package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.utils;

import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Livro;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LeituraLivroUtils {

    public static long calcularPontuacaoLeitura(Livro livro) {
        return (long) Math.ceil(livro.getNumeroPaginas() / 100.0);
    }

}
