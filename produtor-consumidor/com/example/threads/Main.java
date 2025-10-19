package com.example.threads;

public class Main {

    public static void main(String[] args) {
        final int CAPACIDADE = 5; 
        final int N = 20;        
        final int POISON_PILL = -1;

        SharedList shared = new SharedList(CAPACIDADE);

        Thread T1 = new Thread(() -> {
            try {
                for (int i = 1; i <= N; i++) {
                    shared.put(i);
                    System.out.println("[T1] Produziu: " + i);
                  
                    Thread.sleep(50);
                }
       
                shared.put(POISON_PILL);
                System.out.println("[T1] Produziu POISON_PILL e finalizou.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "T1-Producer");

        Thread T2 = new Thread(() -> {
            try {
                while (true) {
                    int value = shared.take();
                    if (value == POISON_PILL) {
                        System.out.println("[T2] Recebeu POISON_PILL. Encerrando consumo.");
                        break;
                    }
                    System.out.println("[T2] Consumiu: " + value);
                    
                    Thread.sleep(80);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "T2-Consumer");

        T1.start();
        T2.start();

        try {
            T1.join();
            T2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Execução concluída.");
    }
}
