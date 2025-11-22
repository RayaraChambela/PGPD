/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simulador.atendimento.caixas;

/**
 *
 * @author racha
 */

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String... args) {

        final int NUMERO_SIMULACOES = 1000;
        final List<Double> mediasAtendimento = new ArrayList<>();

        SimulacaoCaixaSupermercado simulador = new SimulacaoCaixaSupermercado();
        
        int numeroCaixas = 1;
        int mediaAtendimentos = 100;
        double mu = 5.0;
        double sigma = 0.5;

        for (int i = 0; i < NUMERO_SIMULACOES; i++) {
            simulador.setNumeroCaixas(numeroCaixas);
            simulador.setMediaAtendimentos(mediaAtendimentos);
            simulador.setMediaTempoAtendimentoPorCliente(mu);
            simulador.setDesvioPadraoTempoAtendimentoPorCliente(sigma);

            double mediaAtendimento = simulador.simular();
            mediasAtendimento.add(mediaAtendimento);
        }

        double media = media(mediasAtendimento);
        double dp = desvioPadrao(mediasAtendimento, media);

        System.out.printf(
            "Média dos tempos de atendimento (%.0f simulações): %.3f min%n",
            (double) NUMERO_SIMULACOES, media
        );
        System.out.printf(
            "Desvio-padrão das médias: %.3f min%n",
            dp
        );
    }

    private static double media(List<Double> xs) {
        double s = 0.0;
        for (double x : xs) s += x;
        return s / xs.size();
    }

    private static double desvioPadrao(List<Double> xs, double m) {
        double s2 = 0.0;
        for (double x : xs) {
            double d = x - m;
            s2 += d * d;
        }
        return Math.sqrt(s2 / (xs.size() - 1));
    }
}
