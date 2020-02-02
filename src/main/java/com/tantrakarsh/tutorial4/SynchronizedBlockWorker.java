package com.tantrakarsh.tutorial4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynchronizedBlockWorker {
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    private List<Integer> list1 = new ArrayList<Integer>();
    private List<Integer> list2 = new ArrayList<Integer>();
    private Random random = new Random();

    public void stageOne() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));
    }

    public void stageTwo() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(random.nextInt(100));
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void work() {
        System.out.println("Work started...");
        long startTime = System.currentTimeMillis();
        process();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime));
        System.out.println("List1 size: "+list1.size()+" ; List2 size: "+list2.size());
    }
}
