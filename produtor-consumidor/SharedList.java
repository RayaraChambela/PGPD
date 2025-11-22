/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.produtor.consumidor;

/**
 *
 * @author racha
 */

import java.util.ArrayList;
import java.util.List;

public class SharedList {

    private final List<Integer> lista = new ArrayList<>();

    /**
     * Adiciona um valor na lista compartilhada.
     * @param valor
     */
    public synchronized void addValue(int valor) {
        // REGIÃO CRÍTICA:
        // Escrita na lista compartilhada. Se duas threads acessarem ao mesmo tempo,
        // pode ocorrer inconsistência. A sincronização garante acesso exclusivo.
        lista.add(valor);
        System.out.println("T1 adicionou: " + valor);

        notifyAll();
    }

    public synchronized Integer getValue() {
        // REGIÃO CRÍTICA:
        // Leitura/remoção da lista compartilhada. Sem sincronização duas threads
        // poderiam tentar remover ao mesmo tempo, causando erro ou dados incorretos.
        while (lista.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }

        Integer valor = lista.remove(0);

        return valor;
    }
}

