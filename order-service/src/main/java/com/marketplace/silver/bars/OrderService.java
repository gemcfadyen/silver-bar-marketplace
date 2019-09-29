package com.marketplace.silver.bars;

public class OrderService {
  private RegisterOrder registerOrder;

  public OrderService(RegisterOrder registerOrder) {
    this.registerOrder = registerOrder;
  }

  public void register(Order order) {
    registerOrder.add(order);
  }
}
