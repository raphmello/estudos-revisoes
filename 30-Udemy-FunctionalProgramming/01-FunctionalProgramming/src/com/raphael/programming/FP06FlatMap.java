package com.raphael.programming;

import java.util.List;
import java.util.stream.Collectors;

public class FP06FlatMap {
    public static void main(String[] args) {
        List<String> courses = List.of("Spring","Spring Boot","API","Microservices","AWS","PCF","Azure","Docker","Kubernetes");
        List<String> courses2 = List.of("Spring","Spring Boot","API","Microservices","AWS","PCF","Azure","Docker","Kubernetes");

        List<List<String>> collect = courses.stream()
                .flatMap(course -> courses2.stream().map(course2 -> List.of(course, course2)))
                .filter(list -> !list.get(0).equals(list.get(1)))
                .filter(list -> list.get(0).length() == list.get(1).length())
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
