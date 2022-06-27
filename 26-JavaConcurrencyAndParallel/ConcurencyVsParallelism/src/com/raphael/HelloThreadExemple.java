package com.raphael;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class HelloThreadExemple {
    public static String result = "";

    public static void hello() {
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        result = result.concat("Hello");
    }

    public static void world()  {
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        result = result.concat(" World");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread helloThread = new Thread(HelloThreadExemple::hello);
        Thread worldThread = new Thread(HelloThreadExemple::world);

        helloThread.start();
        worldThread.start();

        helloThread.join();
        worldThread.join();

        System.out.println("Result is : " + result);
    }
}
