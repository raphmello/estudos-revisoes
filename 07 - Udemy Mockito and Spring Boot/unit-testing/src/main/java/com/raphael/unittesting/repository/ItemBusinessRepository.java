package com.raphael.unittesting.repository;

import com.raphael.unittesting.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemBusinessRepository extends JpaRepository<Item, Integer> {
}
