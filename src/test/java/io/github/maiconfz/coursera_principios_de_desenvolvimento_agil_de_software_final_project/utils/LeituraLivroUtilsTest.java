package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Livro;

class LeituraLivroUtilsTest {

    @Test
    void testCalcularPontuacaoLeitura() {
        // Caso de teste 1: livro com 72 páginas
        Livro livro1 = new Livro();
        livro1.setNumeroPaginas(72);
        long expected1 = 1; // Esperado: 72 / 100 = 0.72 -> arredonda para 1
        long result1 = LeituraLivroUtils.calcularPontuacaoLeitura(livro1);
        assertEquals(expected1, result1);

        // Caso de teste 2: livro com 124 páginas
        Livro livro2 = new Livro();
        livro2.setNumeroPaginas(124);
        long expected2 = 2; // Esperado: 124 / 100 = 1.24 -> arredonda para 2
        long result2 = LeituraLivroUtils.calcularPontuacaoLeitura(livro2);
        assertEquals(expected2, result2);

        // Caso de teste 3: livro com 350 páginas
        Livro livro3 = new Livro();
        livro3.setNumeroPaginas(350);
        long expected3 = 4; // Expected pontuacao = 350 / 100 = 3.5 -> arredonda para 4
        long result3 = LeituraLivroUtils.calcularPontuacaoLeitura(livro3);
        assertEquals(expected3, result3);
    }
}