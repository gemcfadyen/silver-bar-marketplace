package com.marketplace.silver.bars;

import com.marketplace.silver.bars.cancel.CancelOrder;
import com.marketplace.silver.bars.display.Display;
import com.marketplace.silver.bars.register.RegisterOrder;
import com.marketplace.silver.bars.summary.OrderSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class LiveOrderBoardTest {
  private LiveOrderBoard liveOrderBoard;
  private RegisterOrder registerOrder;
  private CancelOrder cancelOrder;
  private OrderSummary orderSummary;
  private Display display;

  @BeforeEach
  public void setup() {
    registerOrder = mock(RegisterOrder.class);
    cancelOrder = mock(CancelOrder.class);
    orderSummary = mock(OrderSummary.class);
    display = mock(Display.class);
    liveOrderBoard = new LiveOrderBoard(registerOrder, cancelOrder, orderSummary, display);
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

    liveOrderBoard.register(order);

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

    liveOrderBoard.cancel(order);

    verify(cancelOrder).cancel(order);
  }

  @Test
  public void summarisesOrders() {
    when(orderSummary.currentState()).thenReturn("current state");

    liveOrderBoard.summarise();

    verify(orderSummary).currentState();
    verify(display).publish("current state");
  }
}
