package com.marketplace.silver.bars;

public interface DataStore {
    void create(Order order);
    void delete(Order order);
}
