package com.raphael.unittesting.service;

import com.raphael.unittesting.model.Item;
import com.raphael.unittesting.repository.ItemBusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemBusinessService {

    @Autowired
    private ItemBusinessRepository itemBusinessRepository;

    public Item retrieveHardcodedItem() {
        return new Item(1,"Ball",10,100);
    }

    public List<Item> retrieveAllItems() {
        List<Item> items = itemBusinessRepository.findAll();
        for (Item item :
                items) {
          item.setValue(item.getPrice() * item.getQuantity());
        }
        return items;
    }
}
