package com.marketplace.silver.bars.register;

import com.marketplace.silver.bars.DataStore;
import com.marketplace.silver.bars.Order;

public class RegisterOrder {
  private final DataStore dataStore;

  public RegisterOrder(DataStore dataStore) {
    this.dataStore = dataStore;
  }

  public void add(Order order) {
    try {
      dataStore.create(order);
    } catch (Exception e) {
      throw new RegisterOrderException();
    }
  }
}
