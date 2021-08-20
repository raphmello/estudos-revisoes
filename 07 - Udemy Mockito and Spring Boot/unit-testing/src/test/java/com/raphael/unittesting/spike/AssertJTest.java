package com.raphael.unittesting.spike;

import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

public class AssertJTest {

    @Test
    public void learning() {
        List<Integer> numbers = Arrays.asList(12,15,45);
        Assertions.assertThat(numbers)
                .hasSize(3)
                .contains(12,15)
                .allMatch(x -> x > 10)
                .allMatch(x -> x < 100)
                .noneMatch(x -> x < 0);
//        MatcherAssert.assertThat(numbers, Matchers.hasSize(3));
//        MatcherAssert.assertThat(numbers, Matchers.hasItems(12,45));
//        MatcherAssert.assertThat(numbers, Matchers.everyItem(Matchers.greaterThan(10)));
//        MatcherAssert.assertThat(numbers, Matchers.everyItem(Matchers.lessThan(100)));

        Assertions.assertThat("").isEmpty();
        Assertions.assertThat("ABCDE").isNotEmpty();
        Assertions.assertThat("ABCDE")
                .contains("BCD")
                .startsWith("ABC")
                .endsWith("CDE");
//        MatcherAssert.assertThat("ABC", Matchers.containsString("ABC"));
//        MatcherAssert.assertThat("ABCDE", Matchers.startsWith("ABC"));
//        MatcherAssert.assertThat("ABCDE", Matchers.endsWith("CDE"));
    }
}
