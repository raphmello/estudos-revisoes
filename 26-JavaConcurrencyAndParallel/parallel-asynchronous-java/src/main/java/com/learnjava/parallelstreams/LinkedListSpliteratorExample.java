package com.learnjava.parallelstreams;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;

public class LinkedListSpliteratorExample {

    public List<Integer> multiplyEachValue(LinkedList<Integer> inputList, int multiplyValue, boolean isParrallel) {

        startTimer();
        Stream<Integer> integerStream = inputList.stream();

        if (isParrallel)
            integerStream.parallel();

        List<Integer> resultList = integerStream
                .map(number -> number * multiplyValue)
                .collect(Collectors.toList());

        timeTaken();
        return resultList;
    }

}
