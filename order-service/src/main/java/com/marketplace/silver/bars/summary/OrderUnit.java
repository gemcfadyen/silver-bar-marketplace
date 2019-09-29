package com.marketplace.silver.bars.summary;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;

public class OrderUnit {
  private Map<BigDecimal, Double> orders;

  public OrderUnit(Map<BigDecimal, Double> orders) {
    this.orders = orders;
  }

  public OrderUnit(TreeMap<BigDecimal, Double> orders) {
    this.orders = orders;
  }

  public void putAll(OrderUnit orders) {
    this.orders.putAll(orders.getRawData());
  }

  public void forEach(BiConsumer action) {
    this.orders.forEach(action);
  }

  public Map<BigDecimal, Double> getRawData() {
    return this.orders;
  }
}
