package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorldException {

    private HelloWorldService hws;

    public CompletableFutureHelloWorldException(HelloWorldService hws) {
        this.hws = hws;
    }

    public String helloWorld_3_async_calls_handle() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String helloWorld = hello
                .handle((s, throwable) -> {
                    if (throwable != null) {
                        log("Exception is :" + throwable.getMessage() + " " + s);
                        return "";
                    }
                    return s;
        })
                .thenCombine(world, (h, w) -> h + w)
                .handle((s, throwable) -> {
                    if (throwable != null) {
                        log("Exception is from world :" + throwable.getMessage() + " " + s);
                        return "";
                    }
                    return s;
                })
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();

        return helloWorld;
    }

    public String helloWorld_3_async_calls_exceptionally() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String helloWorld = hello
                .exceptionally((throwable) -> {
                    log("Exception is :" + throwable.getMessage());
                    return "";
                })
                .thenCombine(world, (h, w) -> h + w)
                .exceptionally((throwable) -> {
                    log("Exception is from world :" + throwable.getMessage());
                    return "";
                })
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();

        return helloWorld;
    }

    public String helloWorld_3_async_calls_whenComplete() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String helloWorld = hello
                .whenComplete((s, throwable) -> {
                    log("res is : " + s);
                    if (throwable != null) {
                        log("Exception is :" + throwable.getMessage());
                    }
                })
                .thenCombine(world, (h, w) -> h + w)
                .whenComplete((s, throwable) -> {
                    log("res is : " + s);
                    if (throwable != null) {
                        log("Exception is from world :" + throwable.getMessage());
                    }
                })
                .exceptionally(throwable -> {
                    log("Exception is from exceptionally :" + throwable.getMessage());
                    return "";
                })
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();

        return helloWorld;
    }
}