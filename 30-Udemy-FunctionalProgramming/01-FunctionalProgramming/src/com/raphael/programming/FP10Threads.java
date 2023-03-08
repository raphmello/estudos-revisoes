package com.raphael.programming;

import java.util.stream.IntStream;

public class FP10Threads {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println(Thread.currentThread().getId() + ":" + i);
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Thread thread1 = new Thread(runnable);
        thread1.start();

        Thread thread2 = new Thread(runnable);
        thread2.start();

        Runnable runnable2 = () -> {
            IntStream.range(0, 10000)
                    .forEach(number -> System.out.println(Thread.currentThread().getId() + ":" + number));
        };

        Thread thread3 = new Thread(runnable2);
        thread3.start();

        Thread thread4 = new Thread(runnable2);
        thread4.start();

        Thread thread5 = new Thread(runnable2);
        thread5.start();
    }
}
