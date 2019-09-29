package com.marketplace.silver.bars;

import java.math.BigDecimal;

public class OrderBuilder {
  private String userId;
  private double quantity;
  private BigDecimal pricePerKg;
  private OrderType orderType;

  public static OrderBuilder anOrderBuilder() {
    return new OrderBuilder();
  }

  public Order build() {
    return new Order(userId, quantity, pricePerKg, orderType);
  }

  public OrderBuilder withUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public OrderBuilder withQuantityOf(double quantity) {
    this.quantity = quantity;
    return this;
  }

  public OrderBuilder withPricePerKgOf(BigDecimal pricePerKg) {
    this.pricePerKg = pricePerKg;
    return this;
  }

  public OrderBuilder withOrderType(OrderType orderType) {
    this.orderType = orderType;
    return this;
  }
}
