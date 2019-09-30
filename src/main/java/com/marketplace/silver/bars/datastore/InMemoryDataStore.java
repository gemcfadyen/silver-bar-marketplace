package com.marketplace.silver.bars.datastore;

import com.marketplace.silver.bars.DataStore;
import com.marketplace.silver.bars.Order;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataStore implements DataStore {
  private List<Order> dataStore = new ArrayList<>();

  @Override
  public void create(Order order) {
    dataStore.add(order);
  }

  @Override
  public void delete(Order order) {
    dataStore.remove(order);
  }

  @Override
  public List<Order> getAll() {
    return dataStore;
  }
}
