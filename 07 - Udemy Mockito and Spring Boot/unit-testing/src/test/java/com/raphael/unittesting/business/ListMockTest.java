package com.raphael.unittesting.business;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListMockTest {

    List<String> mock = Mockito.mock(List.class);

    @Test
    public void size_basic() {
        Mockito.when(mock.size()).thenReturn(5);
        assertEquals(5,mock.size());
    }

    @Test
    public void returnDifferentValues() {
        Mockito.when(mock.size()).thenReturn(5).thenReturn(10);
        assertEquals(5,mock.size());
        assertEquals(10,mock.size());
    }

    @Test
    public void returnWithParameters() {
        Mockito.when(mock.get(0)).thenReturn("test");
        assertEquals(null,mock.get(1));
    }

    @Test
    public void returnGenericParameters() {
        Mockito.when(mock.get(Mockito.anyInt())).thenReturn("test");
        assertEquals("test",mock.get(1));
    }

    @Test
    public void verificationBasics() {
        String value1 = mock.get(0);
        String value2 = mock.get(1);
        Mockito.verify(mock).get(0);
        Mockito.verify(mock,Mockito.times(2)).get(Mockito.anyInt());
        Mockito.verify(mock, Mockito.atLeast(1)).get(Mockito.anyInt());
        Mockito.verify(mock, Mockito.atLeastOnce()).get(Mockito.anyInt());
        Mockito.verify(mock, Mockito.atMost(2)).get(Mockito.anyInt());
        Mockito.verify(mock, Mockito.never()).get(2);
    }

    @Test
    public void argumentCapturing() {
        mock.add("SomeString");
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(mock).add(captor.capture());
        assertEquals("SomeString",captor.getValue());
    }

    @Test
    public void multipleArgumentCapturing() {
        mock.add("SomeString1");
        mock.add("SomeString2");
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(mock,Mockito.times(2)).add(captor.capture());
        List<String> allValues = captor.getAllValues();
        assertEquals("SomeString1",allValues.get(0));
    }

    @Test
    public void mocking() {
        ArrayList arrayListMock = Mockito.mock(ArrayList.class);
        arrayListMock.get(0); //null
        arrayListMock.size(); //0
        arrayListMock.add("Test");
        arrayListMock.add("Test2");
        arrayListMock.size(); //0
        Mockito.when(arrayListMock.size()).thenReturn(5);
        arrayListMock.size(); //5
    }

    @Test
    public void spying() {
        ArrayList arrayListSpy = Mockito.spy(ArrayList.class);
        arrayListSpy.add("Test");
        arrayListSpy.get(0); //null
        arrayListSpy.size(); //0
        arrayListSpy.add("Test");
        arrayListSpy.add("Test2");
        arrayListSpy.size(); //0
        Mockito.when(arrayListSpy.size()).thenReturn(5);
        arrayListSpy.size(); //5
    }
}
