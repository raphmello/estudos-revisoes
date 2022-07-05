package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static com.learnjava.util.LoggerUtil.log;
import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {

    CompletableFutureHelloWorld cfhw = new CompletableFutureHelloWorld(new HelloWorldService());

    @Test
    void helloWorld() {
        CompletableFuture<String> completableFuture = cfhw.helloWorld();
        completableFuture
                .thenAccept(result -> {
                    assertEquals("HELLO WORLD", result);
                })
                .join();
    }

    @Test
    void helloWorld_multiple_async_calls() {
        String result = cfhw.helloWorld_multiple_async_calls();
        assertEquals("HELLO WORLD!", result);
    }

    @Test
    void helloWorld_3_async_calls() {
        String result = cfhw.helloWorld_3_async_calls();
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_async_calls_log() {
        String result = cfhw.helloWorld_3_async_calls_log();
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_async_calls_async() {
        String result = cfhw.helloWorld_3_async_calls_async();
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_async_calls_custom_threadpool() {
        String result = cfhw.helloWorld_3_async_calls_custom_threadpool();
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_async_calls_custom_threadpool_async() {
        String result = cfhw.helloWorld_3_async_calls_custom_threadpool_async();
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_thenCompose() {
        startTimer();
        CompletableFuture<String> completableFuture = cfhw.helloWorld_thenCompose();
        completableFuture
                .thenAccept(result -> {
                    assertEquals("hello world!", result);
                })
                .join();
        timeTaken();
    }

    @Test
    void anyOf() {
        String any = cfhw.anyOf();
        log(any);
    }
}