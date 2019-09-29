package com.marketplace.silver.bars.summary;

import com.marketplace.silver.bars.DataStore;
import com.marketplace.silver.bars.Order;

import java.util.List;

public class OrderSummary {
  private DataStore dataStore;
  private LiveBoardOrderFormatter liveBoardOrderFormatter;

  public OrderSummary(DataStore dataStore, LiveBoardOrderFormatter liveBoardOrderFormatter) {
    this.dataStore = dataStore;
    this.liveBoardOrderFormatter = liveBoardOrderFormatter;
  }

  public String display() {
    List<Order> allOrders = dataStore.getAll();
    return liveBoardOrderFormatter.summarise(allOrders);
  }
}
