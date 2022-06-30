package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(ints = {6})
    void checkout(int nOfItems) {
        Cart cart = DataSet.createCart(nOfItems);
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
        assertTrue(checkoutResponse.getFinalRate() > 0);
    }
}