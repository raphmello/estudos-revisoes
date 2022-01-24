package com.raphael.unittesting.business;

import com.raphael.unittesting.data.SomeDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SomeBusinessImplMockTest {

    @InjectMocks
    SomeBusinessImpl business;

    @Mock
    SomeDataService dataServiceMock;


    @Test
    public void calculateSumUsingDataService_basic() {
        Mockito.when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1,2,3});
        assertEquals(6,business.calculateSumUsingDataService());
    }

    @Test
    public void calculateSumUsingDataService_empty() {
        Mockito.when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
        assertEquals(0,business.calculateSumUsingDataService());
    }

    @Test
    public void calculateSumUsingDataService_oneValue() {
        Mockito.when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{5});
        assertEquals(5,business.calculateSumUsingDataService());
    }

}