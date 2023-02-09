package com.raphael.programming;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Course {
    private String name;
    private String category;
    private int reviewScore;
    private int noOfStudents;

    public Course(String name, String category, int reviewScore, int noOfStudents) {
        this.name = name;
        this.category = category;
        this.reviewScore = reviewScore;
        this.noOfStudents = noOfStudents;
    }

    @Override
    public String toString() {
        return name + ":" + noOfStudents + ":" + reviewScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }
}

public class FP04CustomClass {
    public static void main(String[] args) {
        List<Course> courses = List.of(
                new Course("Spring", "Framework", 98, 20000),
                new Course("Spring Boot", "Framework", 95, 18000),
                new Course("API", "Microservices", 97, 22000),
                new Course("Microservices", "Microservices", 96, 25000),
                new Course("Fullstack", "Fullstack", 91, 14000),
                new Course("AWS", "Cloud", 92, 21000),
                new Course("Azure", "Cloud", 99, 21000),
                new Course("Docker", "Cloud", 92, 20000),
                new Course("Kubernetes", "Cloud", 91, 20000)
        );

        Predicate<Course> reviewScoreGreaterThen95 = course -> course.getReviewScore() > 95;
        Predicate<Course> reviewScoreGreaterThen90 = course -> course.getReviewScore() > 90;
        Predicate<Course> reviewScoreLessThen90 = course -> course.getReviewScore() < 90;

        boolean allMatch = courses.stream()
                .allMatch(reviewScoreGreaterThen95);
        System.out.println(allMatch);
        boolean noneMatch = courses.stream()
                .noneMatch(reviewScoreLessThen90);
        System.out.println(noneMatch);
        boolean anyMatch = courses.stream()
                .anyMatch(reviewScoreGreaterThen95);
        System.out.println(anyMatch);

        System.out.println("----------------------------------------");
        Comparator<Course> comparingByNumberOfStudentsAsc = Comparator.comparing(Course::getNoOfStudents);
        Comparator<Course> comparingByNumberOfStudentsDesc = Comparator.comparing(Course::getNoOfStudents).reversed();

        List<Course> sortedCourses = courses.stream()
                .sorted(comparingByNumberOfStudentsAsc).collect(Collectors.toList());
        System.out.println(sortedCourses);
        List<Course> reversedSortedCourses = courses.stream()
                .sorted(comparingByNumberOfStudentsDesc).collect(Collectors.toList());
        System.out.println(reversedSortedCourses);

        System.out.println("----------------------------------------");
        Comparator<Course> comparingByNumberOfStudentsAndNoOfReviews = Comparator.comparing(Course::getNoOfStudents)
                .thenComparing(Course::getReviewScore).reversed();
        List<Course> sortedCoursesCompound = courses.stream()
                .sorted(comparingByNumberOfStudentsAndNoOfReviews).collect(Collectors.toList());
        System.out.println(sortedCoursesCompound);

        List<Course> sortedCoursesCompoundLimited = courses.stream()
                .sorted(comparingByNumberOfStudentsAndNoOfReviews)
                .limit(5)
                .collect(Collectors.toList());
        System.out.println(sortedCoursesCompoundLimited);

        List<Course> sortedCoursesCompoundSkip = courses.stream()
                .sorted(comparingByNumberOfStudentsAndNoOfReviews)
                .skip(3)
                .collect(Collectors.toList());
        System.out.println(sortedCoursesCompoundSkip);

        List<Course> sortedCoursesCompoundTakeWhile = courses.stream()
                .sorted(comparingByNumberOfStudentsAndNoOfReviews)
                .takeWhile(course -> course.getReviewScore() >= 95)
                .collect(Collectors.toList());
        System.out.println(sortedCoursesCompoundTakeWhile);

        List<Course> sortedCoursesCompoundDropWhile = courses.stream()
                .sorted(comparingByNumberOfStudentsAndNoOfReviews)
                .dropWhile(course -> course.getReviewScore() >= 95)
                .collect(Collectors.toList());
        System.out.println(sortedCoursesCompoundDropWhile);

        System.out.println("------------------------------------------------");
        System.out.println(courses.stream()
                .max(comparingByNumberOfStudentsAndNoOfReviews));

        System.out.println("------------------------------------------------");
        System.out.println(courses.stream()
                .min(comparingByNumberOfStudentsAndNoOfReviews));

        System.out.println("------------------------------------------------");
        System.out.println(courses.stream()
                .filter(reviewScoreGreaterThen95)
                .mapToInt(Course::getNoOfStudents)
                .sum());

        System.out.println("------------------------------------------------");
        System.out.println(courses.stream()
                .filter(reviewScoreGreaterThen95)
                .mapToInt(Course::getNoOfStudents)
                .average());

        System.out.println("------------------------------------------------");
        System.out.println(courses.stream()
                .filter(reviewScoreGreaterThen95)
                .mapToInt(Course::getNoOfStudents)
                .count());

        System.out.println("------------------------------------------------");
        System.out.println(courses.stream()
                .filter(reviewScoreGreaterThen95)
                .mapToInt(Course::getNoOfStudents)
                .max());

        System.out.println("------------------------------------------------");
        System.out.println(courses.stream()
                .collect(Collectors.groupingBy(Course::getCategory, Collectors.counting())));

        System.out.println("------------------------------------------------");
        System.out.println(courses.stream()
                .collect(Collectors.groupingBy(Course::getCategory, Collectors.maxBy(Comparator.comparing(Course::getReviewScore)))));

        System.out.println("------------------------------------------------");
        System.out.println(courses.stream()
                .collect(Collectors.groupingBy(Course::getCategory, Collectors.mapping(Course::getName, Collectors.toList()))));
    }
}
