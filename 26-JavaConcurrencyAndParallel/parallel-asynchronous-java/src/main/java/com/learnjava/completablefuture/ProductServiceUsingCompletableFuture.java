package com.learnjava.completablefuture;

import com.learnjava.domain.*;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

public class ProductServiceUsingCompletableFuture {
    private ProductInfoService productInfoService;
    private ReviewService reviewService;
    private InventoryService inventoryService;

    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService, InventoryService inventoryService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
        this.inventoryService = inventoryService;
    }

    public Product retrieveProductDetails(String productId) {
        stopWatch.start();

        CompletableFuture<ProductInfo> futureProductInfo = CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId));
        CompletableFuture<Review> futureReview = CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product product = futureProductInfo
                .thenCombine(futureReview, (productInfo, review) -> new Product(productId, productInfo, review))
                .join();

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return product;
    }

    public CompletableFuture<Product> retrieveProductDetails_approach2(String productId) {
        stopWatch.start();

        CompletableFuture<ProductInfo> futureProductInfo = CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId));
        CompletableFuture<Review> futureReview = CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        return futureProductInfo
                .thenCombine(futureReview, (productInfo, review) -> new Product(productId, productInfo, review));

    }

    public Product retrieveProductDetailsWithInventory(String productId) {
        stopWatch.start();

        CompletableFuture<ProductInfo> futureProductInfo = CompletableFuture
                .supplyAsync(() -> productInfoService.retrieveProductInfo(productId))
                .thenApply((productInfo) -> {
                    List<ProductOption> productOptions = updateInventory(productInfo);
                    productInfo.setProductOptions(productOptions);
                    return productInfo;
                });

        CompletableFuture<Review> futureReview = CompletableFuture
                .supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product product = futureProductInfo
                .thenCombine(futureReview, (productInfo, review) -> new Product(productId, productInfo, review))
                .join();

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return product;
    }

    public Product retrieveProductDetailsWithInventory2(String productId) {
        stopWatch.start();

        CompletableFuture<ProductInfo> futureProductInfo = CompletableFuture
                .supplyAsync(() -> productInfoService.retrieveProductInfo(productId))
                .thenApply((productInfo) -> {
                    List<ProductOption> productOptions = updateInventory2(productInfo);
                    productInfo.setProductOptions(productOptions);
                    return productInfo;
                });

        CompletableFuture<Review> futureReview = CompletableFuture
                .supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product product = futureProductInfo
                .thenCombine(futureReview, (productInfo, review) -> new Product(productId, productInfo, review))
                .join();

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return product;
    }

    private List<ProductOption> updateInventory(ProductInfo productInfo) {

        return productInfo.getProductOptions()
                .stream()
                .map(productOption -> {
                    Inventory inventory = inventoryService.addInventory(productOption);
                    productOption.setInventory(inventory);
                    return productOption;
                })
                .collect(Collectors.toList());

    }

    private List<ProductOption> updateInventory2(ProductInfo productInfo) {

        List<CompletableFuture<ProductOption>> productOptionList = productInfo.getProductOptions()
                .stream()
                .map(productOption -> {
                    return CompletableFuture.supplyAsync(() -> inventoryService.addInventory(productOption))
                            .thenApply(inventory -> {
                                productOption.setInventory(inventory);
                                return productOption;
                            });
                })
                .collect(Collectors.toList());

        return productOptionList.stream()
                .map(CompletableFuture::join).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        InventoryService inventoryService = new InventoryService();
        ProductServiceUsingCompletableFuture productService = new ProductServiceUsingCompletableFuture(productInfoService, reviewService, inventoryService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);

    }
}
