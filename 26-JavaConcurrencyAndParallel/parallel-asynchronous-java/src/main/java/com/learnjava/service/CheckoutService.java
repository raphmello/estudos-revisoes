package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CartItem;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class CheckoutService {

    private PriceValidatorService priceValidatorService;

    public CheckoutService(PriceValidatorService priceValidatorService) {
        this.priceValidatorService = priceValidatorService;
    }

    public CheckoutResponse checkout(Cart cart) {
        stopWatch.reset();
        startTimer();

        List<CartItem> priceValidationList = cart.getCartItemList()
                .parallelStream()
                .peek(cartItem -> {
                    boolean isPriceInvalid = priceValidatorService.isCartItemInvalid(cartItem);
                    cartItem.setExpired(isPriceInvalid);
                })
                .filter(CartItem::isExpired)
                .collect(Collectors.toList());

        timeTaken();
        if (priceValidationList.size() > 0) {
            return new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList);
        }

//        double finalPrice = calculateFinalPrice(cart);
        double finalPrice = calculateFinalPrice_reduce(cart);
        log("Final price is " + finalPrice);
        return new CheckoutResponse(CheckoutStatus.SUCCESS, finalPrice);
    }

    private double calculateFinalPrice(Cart cart) {
        return cart.getCartItemList()
                .parallelStream()
                .map(item -> item.getQuantity() * item.getRate())
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private double calculateFinalPrice_reduce(Cart cart) {
        return cart.getCartItemList()
                .parallelStream()
                .map(item -> item.getQuantity() * item.getRate())
                .reduce(0., Double::sum);
    }
}
