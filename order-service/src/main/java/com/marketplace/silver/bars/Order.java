package com.marketplace.silver.bars;

import java.math.BigDecimal;

public class Order {
  private final String userId;
  private final float quantity;
  private final BigDecimal pricePerKg;
  private final OrderType orderType;

  public Order(String userId, float quantity, BigDecimal pricePerKg, OrderType orderType) {
    this.userId = userId;
    this.quantity = quantity;
    this.pricePerKg = pricePerKg;
    this.orderType = orderType;
  }
}
