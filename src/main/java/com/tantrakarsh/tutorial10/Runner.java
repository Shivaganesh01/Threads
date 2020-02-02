package com.tantrakarsh.tutorial10;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    private int value = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            value++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();
        System.out.println("Waiting...");
        condition.await();
        System.out.println("WokeUp...");
        try {
            increment();
        }
        finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();
        System.out.println("Press Enter key...");
        new Scanner(System.in).nextLine();
        System.out.println("Enter key pressed");
        condition.signal();
        System.out.println("Signalled...");
        try {
            increment();
        }
        finally {
            lock.unlock();
        }
    }

    public void finished(){
        System.out.println("Value is: "+value);
    }
}
