package com.raphael.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CFTest {

    public static Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {
        work();
    }

    private static void work() {
        ExecutorService executorService = new ForkJoinPool(20);
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "resposta1";
        }, executorService).orTimeout(2, TimeUnit.SECONDS)
                .exceptionally(e -> {
                    System.out.println(e + e.getMessage());
                    return "";
                });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "resposta2";
        }, executorService).orTimeout(2, TimeUnit.SECONDS)
                .exceptionally(e -> {
                    System.out.println(e.getMessage());
                    return null;
                });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "resposta3";
        }, executorService).orTimeout(2, TimeUnit.SECONDS)
                .exceptionally(e -> {
                    System.out.println(e.getMessage());
                    return null;
                });

        CompletableFuture.allOf(future1, future2, future3);

        String resposta1 = future1.join();
        String resposta2 = future2.join();
        String resposta3 = future3.join();

        executorService.shutdown();

        System.out.println("Todos terminaram: " + resposta1 + " : " + resposta2 + " : " + resposta3);
    }

}
