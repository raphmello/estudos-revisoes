package com.raphael.programming;

import java.util.List;

public class FP01Functional {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
        printAllNumbersInListFuncional(numbers);
        printEvenNumbersInListFuncional(numbers);
    }

    private static void printAllNumbersInListFuncional(List<Integer> numbers) {
        numbers.forEach(System.out::println);
    }

    private static void printEvenNumbersInListFuncional(List<Integer> numbers) {
        numbers.stream()
//                .filter(FP01Functional::isEven)
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);
    }

    private static boolean isEven(Integer n) {
        return n % 2 == 0;
    }
}
