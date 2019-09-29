package com.marketplace.silver.bars;

import com.marketplace.silver.bars.register.RegisterOrder;

public class OrderService {
  private RegisterOrder registerOrder;

  public OrderService(RegisterOrder registerOrder) {
    this.registerOrder = registerOrder;
  }

  public void register(Order order) {
    registerOrder.add(order);
  }
}
