package com.raphael.unittesting.controller;

import com.raphael.unittesting.model.Item;
import com.raphael.unittesting.service.ItemBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemBusinessService itemBusinessService;

    @GetMapping("/dummy-item")
    public Item dummyItem() {
        return new Item(1,"Ball",10,100);
    }

    @PostMapping("/dummy-item")
    public String postItem() {
        return "/item/";
    }

    @GetMapping("/item-from-business-service")
    public Item itemFromBusinessService() {
        return itemBusinessService.retrieveHardcodedItem();
    }

    @GetMapping("/all-items")
    public List<Item> allItems() {
        return itemBusinessService.retrieveAllItems();
    }
}
