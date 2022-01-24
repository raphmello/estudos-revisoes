package com.raphael.unittesting.spike;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class HamcrestMachersTest {

    @Test
    public void learning() {
        List<Integer> numbers = Arrays.asList(12,15,45);
        MatcherAssert.assertThat(numbers, Matchers.hasSize(3));
        MatcherAssert.assertThat(numbers, Matchers.hasItems(12,45));
        MatcherAssert.assertThat(numbers, Matchers.everyItem(Matchers.greaterThan(10)));
        MatcherAssert.assertThat(numbers, Matchers.everyItem(Matchers.lessThan(100)));

        MatcherAssert.assertThat("ABC", Matchers.containsString("ABC"));
        MatcherAssert.assertThat("ABCDE", Matchers.startsWith("ABC"));
        MatcherAssert.assertThat("ABCDE", Matchers.endsWith("CDE"));
    }
}
