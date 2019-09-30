package com.marketplace.silver.bars;

import com.marketplace.silver.bars.cancel.CancelOrder;
import com.marketplace.silver.bars.display.Display;
import com.marketplace.silver.bars.register.RegisterOrder;
import com.marketplace.silver.bars.summary.OrderSummary;

public class LiveOrderBoard {
  private RegisterOrder registerOrder;
  private CancelOrder cancelOrder;
  private OrderSummary orderSummary;
  private Display display;

  public LiveOrderBoard(
      RegisterOrder registerOrder, CancelOrder cancelOrder, OrderSummary orderSummary, Display display) {
    this.registerOrder = registerOrder;
    this.cancelOrder = cancelOrder;
    this.orderSummary = orderSummary;
    this.display = display;
  }

  public void register(Order order) {
    registerOrder.add(order);
  }

  public void cancel(Order order) {
    cancelOrder.cancel(order);
  }

  public void summarise() {
    display.publish(orderSummary.currentState());
  }
}
