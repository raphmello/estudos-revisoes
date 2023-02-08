package com.raphael.programming;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FP03FunctionalInterfaces {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        Predicate<Integer> evenPredicate = x -> x % 2 == 0;
        Predicate<Integer> evenPredicate2 = new Predicate<Integer>() {
            @Override
            public boolean test(Integer x) {
                return x % 2 == 0;
            }
        };

        Function<Integer, Integer> squareFunction = x -> x * x;
        Consumer<Integer> sysoutConsumer = System.out::println;

        numbers.stream()
                .filter(evenPredicate)
                .map(squareFunction)
                .forEach(sysoutConsumer);

        System.out.println("----------------------------------------------------------------");
        BinaryOperator<Integer> sumOperator = Integer::sum;
        Integer sum = numbers.stream()
                .reduce(0, sumOperator);

        Integer sum2 = numbers.stream()
                .reduce(0, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer x1, Integer x2) {
                        return x1 + x2;
                    }
                });
        System.out.println(sum);
        System.out.println(sum2);
    }
}
