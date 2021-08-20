package com.raphael.unittesting.business;

import com.raphael.unittesting.data.SomeDataService;
import com.raphael.unittesting.model.Item;
import com.raphael.unittesting.repository.ItemBusinessRepository;
import com.raphael.unittesting.service.ItemBusinessService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ItemBusinessServiceTest {

    @InjectMocks
    private ItemBusinessService itemBusinessService;

    @Mock
    private ItemBusinessRepository itemBusinessRepositoryMock;

    @Test
    public void retrieveAllItemTest() {
        Mockito.when(itemBusinessRepositoryMock.findAll())
                .thenReturn(Arrays.asList(new Item(2,"Item2",10,10), new Item(3,"Item3",20,20)));
        List<Item> items = itemBusinessService.retrieveAllItems();
        assertEquals(100,items.get(0).getValue());
        assertEquals(400,items.get(1).getValue());
    }

}