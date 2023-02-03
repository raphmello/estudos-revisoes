package com.raphael.programming;

import java.util.List;

public class FP01Exercices {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
        numbers.stream()
                .filter(FP01Exercices::printOddNumbersInListFunctional)
                .forEach(System.out::println);
        System.out.println("-----------------------------------------");

        List<String> courses = List.of("Spring","Spring Boot","API","Microservices","AWS","PCF","Azure","Docker","Kubernetes");
        courses.forEach(System.out::println);
        System.out.println("-----------------------------------------");
        courses.stream()
                .filter(course -> course.contains("Spring"))
                .forEach(System.out::println);
        System.out.println("-----------------------------------------");
        courses.stream()
                .filter(course -> course.length() >= 4)
                .forEach(System.out::println);
        System.out.println("-----------------------------------------");
        printSquaresOfEvenNumbersInListFunctional(numbers);
        System.out.println("-----------------------------------------");
        printCubesOfOddNumbersInListFunctional(numbers);
        System.out.println("-----------------------------------------");
        printNumberOfCharactersInCourseName(courses);
    }

    private static boolean printOddNumbersInListFunctional(Integer number) {
        return number % 2 != 0;
    }

    private static void printSquaresOfEvenNumbersInListFunctional(List<Integer> numbers) {
        numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .forEach(System.out::println);
    }

    private static void printCubesOfOddNumbersInListFunctional(List<Integer> numbers) {
        numbers.stream()
                .filter(n -> n % 2 != 0)
                .map(n -> n * n * n)
                .forEach(System.out::println);
    }

    private static void printNumberOfCharactersInCourseName(List<String> courses) {
        courses.stream()
                .map(String::length)
                .forEach(System.out::println);
    }
}
