package com.raphael.programming;

import java.math.BigInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class FP05CreatingStreams {
    public static void main(String[] args) {
        IntStream intStream = IntStream.range(0, 10);
        intStream.map(n -> n * n)
                .forEach(System.out::println);

        IntStream intStreamClosed = IntStream.rangeClosed(0, 10);
        intStreamClosed.map(n -> n * n)
                .forEach(System.out::println);
        System.out.println("-------------------------");
        int sum = IntStream.iterate(1, e -> e + 2).limit(10).peek(System.out::println).sum();
        System.out.println(sum);

        System.out.println(IntStream.iterate(1, e -> e + 2).limit(10).peek(System.out::println).boxed().collect(Collectors.toList()));

        System.out.println(LongStream.rangeClosed(1,50).reduce(1L, (left, right) -> left * right)); //too big value for a Long

        System.out.println(LongStream.rangeClosed(1,50).mapToObj(BigInteger::valueOf).reduce(BigInteger.ONE, BigInteger::multiply));

    }
}
