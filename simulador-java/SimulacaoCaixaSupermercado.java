/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.simulador.atendimento.caixas;

/**
 *
 * @author racha
 */
public class SimulacaoCaixaSupermercado {

    private int numeroCaixas;
    private int mediaAtendimentos; // N clientes simulados
    private double mediaTempoAtendimentoPorCliente; // μ
    private double desvioPadraoTempoAtendimentoPorCliente; // σ

    // epsilon (limite inferior > 0)
    private static final double TEMPO_MINIMO_ATENDIMENTO = 0.1;

    // semente fixa só pra deixar os resultados reprodutíveis
    private final java.util.Random rng = new java.util.Random(42);

    public void setNumeroCaixas(int n) { 
        this.numeroCaixas = n; 
    }

    public void setMediaAtendimentos(int n) { 
        this.mediaAtendimentos = n; 
    }

    public void setMediaTempoAtendimentoPorCliente(double mu) {
        this.mediaTempoAtendimentoPorCliente = mu;
    }

    public void setDesvioPadraoTempoAtendimentoPorCliente(double sigma) {
        this.desvioPadraoTempoAtendimentoPorCliente = sigma;
    }

    /**
     * Gera um tempo de atendimento S_i a partir de:
     * S_i = max(ε, μ + σ * Z), Z ~ N(0, 1)
     */
    private double tempoAtendimentoNormalTruncado() {
        double z = rng.nextGaussian(); // Z ~ N(0,1)
        double s = mediaTempoAtendimentoPorCliente +
                   desvioPadraoTempoAtendimentoPorCliente * z;
        // truncagem em ε
        return (s < TEMPO_MINIMO_ATENDIMENTO) ? TEMPO_MINIMO_ATENDIMENTO : s;
    }

    /**
     * Simula mediaAtendimentos clientes sendo atendidos.
     *
     * Modelo simplificado:
     *  - Primeiro calcula a média do tempo de atendimento de UM caixa.
     *  - Depois divide pelo número de caixas, assumindo que a carga
     *    é dividida igualmente entre eles (atendimento em paralelo).
     */
    public double simular() {
        double soma = 0.0;

        for (int i = 0; i < mediaAtendimentos; i++) {
            soma += tempoAtendimentoNormalTruncado();
        }

        double mediaUmCaixa = soma / mediaAtendimentos;

        if (numeroCaixas <= 0) {
            // fallback só pra evitar divisão por zero se esquecer de setar
            return mediaUmCaixa;
        }

        // “tempo médio efetivo por cliente” considerando atendimento em paralelo
        return mediaUmCaixa / numeroCaixas;
    }
}
