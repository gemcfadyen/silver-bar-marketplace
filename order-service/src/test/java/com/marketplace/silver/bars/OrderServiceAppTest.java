package com.marketplace.silver.bars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceAppTest {
  @Test
  public void helloTest() {
    OrderServiceApp app = new OrderServiceApp();

    Assertions.assertEquals(app.greeting(), "hello");
  }
}
