package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceUsingCompletableFutureTest {

    ProductServiceUsingCompletableFuture service = new ProductServiceUsingCompletableFuture(new ProductInfoService(), new ReviewService());

    @Test
    void retrieveProductDetails() {
        Product product = service.retrieveProductDetails("1");
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size()>0);
        assertNotNull(product.getReview());
    }
}