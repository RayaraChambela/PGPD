/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.produtor.consumidor;

/**
 *
 * @author racha
 */

public class Consumer implements Runnable {

    private final SharedList sharedList;

    public Consumer(SharedList sharedList) {
        this.sharedList = sharedList;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            Integer valor = sharedList.getValue();
            if (valor == null) {
                break;
            }
            System.out.println("T2 consumiu: " + valor);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
