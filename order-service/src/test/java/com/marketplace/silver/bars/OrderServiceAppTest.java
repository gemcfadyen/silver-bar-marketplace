package com.marketplace.silver.bars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceAppTest {
  @Test
  public void helloTest() {
    OrderServiceApp app = new OrderServiceApp();

    assertEquals(app.greeting(), "hello");
  }
}
