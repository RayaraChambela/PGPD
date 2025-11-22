/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.produtor.consumidor;

/**
 *
 * @author racha
 */

public class Main {

    public static void main(String[] args) {
        SharedList sharedList = new SharedList();

        Thread t1 = new Thread(new Producer(sharedList), "T1-Producer");
        Thread t2 = new Thread(new Consumer(sharedList), "T2-Consumer");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Fim da execução.");
    }
}

