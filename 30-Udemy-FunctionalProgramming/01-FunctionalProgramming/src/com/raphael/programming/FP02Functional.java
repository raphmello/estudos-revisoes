package com.raphael.programming;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FP02Functional {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
        List<String> courses = List.of("Spring","Spring Boot","API","Microservices","AWS","PCF","Azure","Docker","Kubernetes");
//        0 12
//        12 9
//        21 13
//        34 4
//        38 6
//        44 2
//        46 4
//        50 12
//        62 15
//        77

        int sum = addUsingFunctional(numbers);
        System.out.println(sum);

        System.out.println("-----------------------------");
        System.out.println(squareEveryNumberAndSum(numbers));
        System.out.println("-----------------------------");
        System.out.println(cubeEveryNumberAndSum(numbers));
        System.out.println("-----------------------------");
        System.out.println(sumOddNumbers(numbers));
        System.out.println("-----------------------------");
        numbers.stream().distinct().sorted().forEach(System.out::println);
        System.out.println("-----------------------------");
        courses.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);
        System.out.println("-----------------------------");
        courses.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        System.out.println("-----------------------------");
        courses.stream().sorted(Comparator.comparing(String::length)).forEach(System.out::println);
        System.out.println("-----------------------------");
        List<Integer> doubledNumbers = getListOfDoubledNumbers(numbers);
        System.out.println(doubledNumbers);
        System.out.println("-----------------------------");
        List<Integer> evenNumbers = getListOfEvenNumbers(numbers);
        System.out.println(evenNumbers);
        System.out.println("-----------------------------");
        List<Integer> courseLengths = getListWithLengthOfCourseTitles(courses);
        System.out.println(courseLengths);

    }

    private static List<Integer> getListOfDoubledNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
    }

    private static List<Integer> getListOfEvenNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
    }

    private static List<Integer> getListWithLengthOfCourseTitles(List<String> courses) {
        return courses.stream()
                .map(String::length)
                .collect(Collectors.toList());
    }

    private static int addUsingFunctional(List<Integer> numbers) {
        return numbers.stream()
//                .reduce(0, (aggregate, nextNumber) -> {
//                    System.out.println(aggregate + " " + nextNumber);
//                    return aggregate + nextNumber;
//                });
                .reduce(0, Integer::sum);
    }

    private static int squareEveryNumberAndSum(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(n -> n * n)
                .sum();

    }

    private static int cubeEveryNumberAndSum(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(n -> n * n * n)
                .sum();
    }

    private static int sumOddNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 != 0)
                .reduce(0, Integer::sum);
    }

}
