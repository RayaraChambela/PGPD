package com.example.threads;

import java.util.ArrayList;
import java.util.List;

public class SharedList {
    private final List<Integer> buffer = new ArrayList<>();
    private final int capacity;

    public SharedList(int capacity) {
        this.capacity = capacity;
    }

    //REGIÃO CRÍTICA: leitura/escrita do buffer compartilhado.
    public synchronized void put(int value) throws InterruptedException {
        while (buffer.size() == capacity) {
            wait();
        }
        buffer.add(value); // ACESSO COMPARTILHADO (crítico)
        notifyAll();
    }

    //REGIÃO CRÍTICA: leitura/escrita do buffer compartilhado.
    public synchronized int take() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();
        }
        int value = buffer.remove(0); // ACESSO COMPARTILHADO (crítico)
        notifyAll();
        return value;
    }
}
