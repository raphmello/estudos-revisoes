package com.raphael.unittesting.data;

import com.raphael.unittesting.model.Item;
import com.raphael.unittesting.repository.ItemBusinessRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private ItemBusinessRepository repository;

    @Test
    public void testFindAll() {
        List<Item> items = repository.findAll();
        Assertions.assertEquals(3,items.size());
    }
}
