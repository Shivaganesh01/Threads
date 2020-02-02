package com.tantrakarsh.tutorial9;

import java.util.LinkedList;
import java.util.Random;

public class Processor {
    private final int LIMIT = 10;
    private LinkedList<Integer> linkedList = new LinkedList<Integer>();
    private Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            System.out.println("Producing...");
            synchronized (lock) {
                while (linkedList.size() == LIMIT) {
                    lock.wait();
                }
                linkedList.add(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        Random random = new Random();
        while (true) {
            System.out.println("Consuming...");
            synchronized (lock) {
                while (linkedList.size() == 0) {
                    lock.wait();
                }
                System.out.println("List size is: " + linkedList.size());
                int value = linkedList.removeFirst();
                System.out.println("Removed value is: " + value);
                lock.notify();
            }
            Thread.sleep(random.nextInt(1000));
        }
    }
}
