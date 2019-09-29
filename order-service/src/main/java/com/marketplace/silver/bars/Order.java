package com.marketplace.silver.bars;

import java.math.BigDecimal;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Order order = (Order) o;
    return Float.compare(order.quantity, quantity) == 0
        && Objects.equals(userId, order.userId)
        && Objects.equals(pricePerKg, order.pricePerKg)
        && orderType == order.orderType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, quantity, pricePerKg, orderType);
  }
}
