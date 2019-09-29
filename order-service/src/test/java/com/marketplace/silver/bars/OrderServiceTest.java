package com.marketplace.silver.bars;

import com.marketplace.silver.bars.register.RegisterOrder;
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
    Order order =
        OrderBuilder.anOrderBuilder()
            .withUserId("user1")
            .withQuanityOf(3.5f)
            .withPricePerKgOf(new BigDecimal(303))
            .withOrderType(OrderType.SELL)
            .build();

    orderService.register(order);

    verify(registerOrder).add(order);
  }
}
