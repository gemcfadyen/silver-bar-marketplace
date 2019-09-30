package com.marketplace.silver.bars;

import java.util.List;

public interface DataStore {
  void create(Order order);

  void delete(Order order);

  List<Order> getAll();
}
