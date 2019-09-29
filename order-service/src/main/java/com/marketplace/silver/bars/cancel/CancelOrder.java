package com.marketplace.silver.bars.cancel;

import com.marketplace.silver.bars.DataStore;
import com.marketplace.silver.bars.Order;

public class CancelOrder {
  private DataStore dataStore;

  public CancelOrder(DataStore dataStore) {
    this.dataStore = dataStore;
  }

  public void cancel(Order order) {
    try {
      dataStore.delete(order);
    } catch (Exception e) {
      throw new CancelOrderException();
    }
  }
}
