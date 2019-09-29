package com.marketplace.silver.bars;

import com.marketplace.silver.bars.cancel.CancelOrder;
import com.marketplace.silver.bars.register.RegisterOrder;

public class OrderService {
  private RegisterOrder registerOrder;
  private CancelOrder cancelOrder;

  public OrderService(RegisterOrder registerOrder, CancelOrder cancelOrder) {
    this.registerOrder = registerOrder;
    this.cancelOrder = cancelOrder;
  }

  public void register(Order order) {
    registerOrder.add(order);
  }

  public void cancel(Order order) {
    cancelOrder.cancel(order);
  }
}
