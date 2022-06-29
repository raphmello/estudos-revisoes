package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static com.learnjava.util.CommonUtil.stopWatch;
import static org.junit.jupiter.api.Assertions.*;

class ArrayListSpliteratorExampleTest {

    ArrayListSpliteratorExample arrayListSpliteratorExample = new ArrayListSpliteratorExample();

    @RepeatedTest(5)
    void multiplyEachValue() {
        int size = 1000000;
        ArrayList<Integer> inputList = DataSet.generateArrayList(size);
        List<Integer> resultList = arrayListSpliteratorExample.multiplyEachValue(inputList, 2, false);
        assertEquals(size, resultList.size());
        stopWatch.reset();
    }

    @RepeatedTest(5)
    void multiplyEachValue_parallel() {
        int size = 1000000;
        ArrayList<Integer> inputList = DataSet.generateArrayList(size);
        List<Integer> resultList = arrayListSpliteratorExample.multiplyEachValue(inputList, 2, true);
        assertEquals(size, resultList.size());
        stopWatch.reset();
    }
}