package com.tantrakarsh.tutorial2.demo1;

import java.util.Scanner;

class Process extends Thread {
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Hello from " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutDown() {
        running = false;
    }
}

public class ThreadSynchronization {
    public static void main(String[] args) {
        Process proc1 = new Process();
        proc1.start();

        System.out.println("Press enter key to stop...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        proc1.shutDown();

    }
}
