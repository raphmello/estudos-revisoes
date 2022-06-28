package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.learnjava.util.CommonUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class ParallelStreamsExampleTest {

    ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();

    @Test
    void
    stringTransformWithParallelSteams() {
        List<String> inputList = DataSet.namesList();

        startTimer();
        List<String> resultList = parallelStreamsExample.stringTransformWithParallelSteams(inputList);
        timeTaken();

        assertEquals(4, resultList.size());
        resultList.forEach(name-> {
            assertTrue(name.contains("-"));
        });
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void
    stringTransformWithParallelSteams_1(boolean isParallel) {
        List<String> inputList = DataSet.namesList();

        startTimer();
        List<String> resultList = parallelStreamsExample.stringTransformWithParallelSteams_1(inputList, isParallel);
        timeTaken();
        stopWatch.reset();
        assertEquals(4, resultList.size());
        resultList.forEach(name-> {
            assertTrue(name.contains("-"));
        });
    }
}