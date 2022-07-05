package com.raphael.example2;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("Intentional Exceptional");
            }
        });

        thread.setName("New Worker Thread");

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A cricital error happened in thread " + thread.getName()
                + " the error is " + e.getMessage());
            }
        });
        thread.start();
    }
}
