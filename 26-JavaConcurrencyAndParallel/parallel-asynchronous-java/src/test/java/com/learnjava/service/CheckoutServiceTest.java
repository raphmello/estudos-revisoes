package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.ForkJoinPool;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.stopWatch;
import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    CheckoutService checkoutService = new CheckoutService(new PriceValidatorService());

    @Test
    void numberOfCores() {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
    }

    @Test
    void parallelism() {
        System.out.println(ForkJoinPool.getCommonPoolParallelism());
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 15,16})
    void checkout(int nOfItems) {
        Cart cart = DataSet.createCart(nOfItems);
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
        assertTrue(checkoutResponse.getFinalRate() > 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {100})
    void modify_parallelism(int nOfItems) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "100");
        Cart cart = DataSet.createCart(nOfItems);
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }
}