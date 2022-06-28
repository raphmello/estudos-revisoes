package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

public class ParallelStreamsExample {
    public static void main(String[] args) {
        List<String> namesList = DataSet.namesList();
        ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();
        stopWatch.start();
        List<String> stringList = parallelStreamsExample.stringTransformWithStreams(namesList);
        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        log("Final Result : "+ stringList);

        stopWatch.reset();

        stopWatch.start();
        stringList = parallelStreamsExample.stringTransformWithParallelSteams(namesList);
        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        log("Final Result : "+ stringList);

    }

    public List<String> stringTransformWithParallelSteams(List<String> namesList) {
        return namesList
                .parallelStream()
                .map(ParallelStreamsExample::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public List<String> stringTransformWithStreams(List<String> namesList) {
        return namesList
                .stream()
                .map(ParallelStreamsExample::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    private static String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }
}
