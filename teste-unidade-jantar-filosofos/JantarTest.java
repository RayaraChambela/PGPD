/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.teste.unidade.jantar.filosofos;

/**
 *
 * @author racha
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JantarTest {

    private Jantar jantar;

    @BeforeEach
    void setUp() {
        jantar = new Jantar();
    }

    @Test
    void iniciarDeveConfigurarFilosofosEGarfosCorretamente() {
        // Act
        jantar.iniciar();

        Filosofo[] filosofos = jantar.getFilosofos();
        Garfo[] garfos = jantar.getGarfos();

        // Verificações básicas de existência
        assertNotNull(filosofos, "O array de filósofos não deve ser nulo");
        assertNotNull(garfos, "O array de garfos não deve ser nulo");

        // Ajuste o número esperado se na sua implementação for diferente
        int NUM_FILOSOFOS = 5;
        assertEquals(NUM_FILOSOFOS, filosofos.length, "Quantidade de filósofos incorreta");
        assertEquals(NUM_FILOSOFOS, garfos.length, "Quantidade de garfos incorreta");

        // Cada filósofo deve ter dois garfos não nulos e distintos
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            Filosofo f = filosofos[i];
            assertNotNull(f, "Filósofo " + i + " não deve ser nulo");

            Garfo garfoEsquerdo = f.getGarfoEsquerdo();
            Garfo garfoDireito = f.getGarfoDireito();

            assertNotNull(garfoEsquerdo, "Garfo esquerdo do filósofo " + i + " não deve ser nulo");
            assertNotNull(garfoDireito, "Garfo direito do filósofo " + i + " não deve ser nulo");
            assertNotSame(garfoEsquerdo, garfoDireito,
                    "Os dois garfos do filósofo " + i + " não podem ser o mesmo objeto");
        }

        // Cada garfo deve ser compartilhado por exatamente 2 filósofos
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            Garfo garfo = garfos[i];
            assertNotNull(garfo, "Garfo " + i + " não deve ser nulo");

            int contadorUso = 0;
            for (Filosofo f : filosofos) {
                if (f.getGarfoEsquerdo() == garfo || f.getGarfoDireito() == garfo) {
                    contadorUso++;
                }
            }

            assertEquals(2, contadorUso,
                    "O garfo " + i + " deve ser compartilhado por exatamente 2 filósofos");
        }

        // Verificar configuração circular (opcional, mas reforça o teste)
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            Filosofo atual = filosofos[i];
            Filosofo proximo = filosofos[(i + 1) % NUM_FILOSOFOS];

            assertSame(atual.getGarfoDireito(), proximo.getGarfoEsquerdo(),
                    "Configuração circular de garfos está incorreta entre filósofo " +
                            i + " e filósofo " + ((i + 1) % NUM_FILOSOFOS));
        }
    }
}
