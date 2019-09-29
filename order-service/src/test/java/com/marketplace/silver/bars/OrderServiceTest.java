package com.marketplace.silver.bars;

import com.marketplace.silver.bars.cancel.CancelOrder;
import com.marketplace.silver.bars.register.RegisterOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class OrderServiceTest {
  private OrderService orderService;
  private RegisterOrder registerOrder;
  private CancelOrder cancelOrder;

  @BeforeEach
  public void setup() {
    registerOrder = mock(RegisterOrder.class);
    cancelOrder = mock(CancelOrder.class);
    orderService = new OrderService(registerOrder, cancelOrder);
  }

  @Test
  public void registersOrder() {
    Order order =
        OrderBuilder.anOrderBuilder()
            .withUserId("user1")
            .withQuantityOf(3.5)
            .withPricePerKgOf(new BigDecimal(303))
            .withOrderType(OrderType.SELL)
            .build();

    orderService.register(order);

    verify(registerOrder).add(order);
  }

  @Test
  public void cancelsOrder() {
    Order order =
            OrderBuilder.anOrderBuilder()
                    .withUserId("user1")
                    .withQuantityOf(3.5)
                    .withPricePerKgOf(new BigDecimal(303))
                    .withOrderType(OrderType.SELL)
                    .build();

    orderService.cancel(order);

    verify(cancelOrder).cancel(order);
  }
}
