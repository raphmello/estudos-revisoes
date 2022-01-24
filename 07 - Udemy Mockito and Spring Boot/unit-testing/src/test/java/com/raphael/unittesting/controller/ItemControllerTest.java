package com.raphael.unittesting.controller;

import com.raphael.unittesting.model.Item;
import com.raphael.unittesting.service.ItemBusinessService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(value = ItemController.class)
class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ItemBusinessService itemBusinessService;

    @Test
    public void getItemTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"id\": 1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
                .andReturn();
    }

    @Test
    public void getItemFromBusinessTest() throws Exception {
        Mockito.when(itemBusinessService.retrieveHardcodedItem()).thenReturn(new Item(1,"Ball",10,100));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/item-from-business-service")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"id\": 1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
                .andReturn();
    }

    @Test
    public void getAllItemsFromDatabaseTest() throws Exception {
        Mockito.when(itemBusinessService.retrieveAllItems())
                .thenReturn(Arrays.asList(new Item(2,"Item2",10,10), new Item(3,"Item3",20,20)));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/all-items")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{id: 2,name:Item2,price:10,quantity:10},{id: 3,name:Item3,price:20,quantity:20}]"))
                .andReturn();

//        Assertions.assertEquals(1000,mvcResult.getResponse().ge);
    }

    @Test
    public void postItemTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/dummy-item")
                .accept(MediaType.APPLICATION_JSON)
                .content("{id: 2,name:Item2,price:10,quantity:10}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());
//        assertEquals("/dummy-item",response.getHeader(HttpHeaders.LOCATION));
    }

}