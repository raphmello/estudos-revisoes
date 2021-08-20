package com.raphael.unittesting.business;

import com.raphael.unittesting.data.SomeDataService;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SomeBusinessImpl {

    private SomeDataService someDataService;

    public void setSomeDataService(SomeDataService someDataService) {
        this.someDataService = someDataService;
    }

    public int calculateSum(int[] data) {
        return Arrays.stream(data).sum();
    }

    public int calculateSumUsingDataService() {
        int[] data = someDataService.retrieveAllData();
        return Arrays.stream(data).sum();
    }
}
