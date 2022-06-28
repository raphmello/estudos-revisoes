package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
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
}