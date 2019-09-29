package com.marketplace.silver.bars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class OrderServiceTest {
  private OrderService orderService;
  private RegisterOrder registerOrder;

  @BeforeEach
  public void setup() {
    registerOrder = mock(RegisterOrder.class);
    orderService = new OrderService(registerOrder);
  }

  @Test
  public void registersOrder() {
    String userId = "user1";
    float quantity = 3.5f;
    BigDecimal pricePerKg = new BigDecimal(303);

    Order order = new Order(userId, quantity, pricePerKg, OrderType.SELL);

    orderService.register(order);

    verify(registerOrder).add(order);
  }
}
