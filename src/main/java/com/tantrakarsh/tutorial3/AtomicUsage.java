package com.tantrakarsh.tutorial3;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicUsage {
    private AtomicInteger counter = new AtomicInteger(0);
    public static void main(String[] args) {
        SynchronizedUsage synchronizedUsage = new SynchronizedUsage();
        synchronizedUsage.doTask();
    }

    public  void incrementCounter(){
        counter.getAndIncrement();
    }

    public void doTask(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000 ; i++) {
                    incrementCounter();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000 ; i++) {
                    incrementCounter();
                }
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Counter Value: "+counter);
    }
}

