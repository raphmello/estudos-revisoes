package com.raphael.programming;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FP03BehaviourParametrization {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        filterAndPrintNumbers(numbers, x -> x % 2 == 0);
        System.out.println("--------------------------------------------------------");
        filterAndPrintNumbers(numbers, x -> x % 2 != 0);
        System.out.println("--------------------------------------------------------");
        filterAndPrintNumbers(numbers, x -> x % 3 == 0);
        System.out.println("--------------------------------------------------------");
        List<Integer> squareNumbers = mapAndReturnList(numbers, x -> x * x);
        System.out.println(squareNumbers);
        System.out.println("--------------------------------------------------------");
        List<Integer> cubeNumbers = mapAndReturnList(numbers, x -> x * x * x);
        System.out.println(cubeNumbers);
    }

    private static List<Integer> mapAndReturnList(List<Integer> numbers, Function<Integer, Integer> mappingFunction) {
        return numbers.stream()
                .map(mappingFunction).collect(Collectors.toList());
    }

    private static void filterAndPrintNumbers(List<Integer> numbers, Predicate<Integer> predicate) {
        numbers.stream()
                .filter(predicate)
                .forEach(System.out::println);
    }
}
