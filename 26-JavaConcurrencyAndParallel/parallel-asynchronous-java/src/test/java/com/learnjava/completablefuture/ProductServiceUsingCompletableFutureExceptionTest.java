package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletableFutureExceptionTest {

    @InjectMocks
    ProductServiceUsingCompletableFuture pscf;

    @Mock
    private ProductInfoService pisMock;
    @Mock
    private ReviewService rsMock;
    @Mock
    private InventoryService isMock;

    @Test
    void retrieveProductDetailsWithInventory2() {
        when(pisMock.retrieveProductInfo(any())).thenCallRealMethod();
        when(rsMock.retrieveReviews(any())).thenThrow(new RuntimeException("Review ERROR"));
        when(isMock.addInventory(any())).thenCallRealMethod();

        Product product = pscf.retrieveProductDetailsWithInventory2("ABC123");
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size()>0);
        product.getProductInfo().getProductOptions()
                .forEach(productOption -> {
                    assertNotNull(productOption.getInventory());
                });
        assertNotNull(product.getReview());
        assertEquals(0,product.getReview().getNoOfReviews());
    }

    @Test
    void retrieveProductDetailsWithInventory_productInfoServiceError() {
        when(pisMock.retrieveProductInfo(any())).thenThrow(new RuntimeException("ProductInfo ERROR"));
        when(rsMock.retrieveReviews(any())).thenCallRealMethod();

        assertThrows(RuntimeException.class, ()->pscf.retrieveProductDetailsWithInventory2("ABC123"));
    }
}