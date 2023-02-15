package com.raphael.programming;

import java.util.ArrayList;
import java.util.List;

public class FP08ModifyingLists {
    public static void main(String[] args) {
        List<String> courses = List.of("Spring","Spring Boot","API","Microservices","AWS","PCF","Azure","Docker","Kubernetes");
        List<String> modifiableCourses = new ArrayList<>(courses);
        modifiableCourses.replaceAll(String::toUpperCase);
        System.out.println(modifiableCourses);

        modifiableCourses.removeIf(str -> str.length() < 6);
        System.out.println(modifiableCourses);

    }
}
