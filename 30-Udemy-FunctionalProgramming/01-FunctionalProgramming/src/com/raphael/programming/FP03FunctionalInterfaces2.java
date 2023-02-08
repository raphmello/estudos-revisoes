package com.raphael.programming;

import java.util.List;
import java.util.Random;
import java.util.function.*;

public class FP03FunctionalInterfaces2 {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        Predicate<Integer> evenPredicate = x -> x % 2 == 0;

        Function<Integer, Integer> squareFunction = x -> x * x;

        Function<Integer, String> stringOutputFunction = x -> x + " ";

        Consumer<Integer> sysoutConsumer = System.out::println;

        BinaryOperator<Integer> sumOperator = Integer::sum;

        Supplier<Integer> randomIntegerSupplier = () -> new Random().nextInt(100);
        System.out.println(randomIntegerSupplier.get());

        System.out.println("----------------------------------------------------------");

        UnaryOperator<Integer> unaryOperator = x -> x * 3;
        System.out.println(unaryOperator.apply(10));

        System.out.println("----------------------------------------------------------");

        BiPredicate<Integer, Integer> biPredicate = (a, b) -> a % b == 0;
        System.out.println(biPredicate.test(10,5));
        System.out.println(biPredicate.test(10,4));

        System.out.println("----------------------------------------------------------");

        BiFunction<String, String, Integer> biFunction = (s1, s2) -> (s1 + s2).length();
        System.out.println(biFunction.apply("SUN", "PLANET"));

        System.out.println("----------------------------------------------------------");

        BiConsumer<Integer, String> biConsumer = (number, string) -> System.out.println(number + " " + string);
        biConsumer.accept(10, "Valid");

        System.out.println("----------------------------------------------------------");

        IntBinaryOperator intBinaryOperator = (x, y) -> x * y;
    }
}
