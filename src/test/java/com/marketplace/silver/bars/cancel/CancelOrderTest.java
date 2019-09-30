package com.marketplace.silver.bars.cancel;

import com.marketplace.silver.bars.DataStore;
import com.marketplace.silver.bars.Order;
import com.marketplace.silver.bars.OrderBuilder;
import com.marketplace.silver.bars.OrderType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CancelOrderTest {
  private CancelOrder cancelOrder;
  private DataStore dataStore;
  private Order order;

  @BeforeEach
  public void setup() {
    dataStore = mock(DataStore.class);
    cancelOrder = new CancelOrder(dataStore);
    order =
        OrderBuilder.anOrderBuilder()
            .withQuantityOf(1.2)
            .withPricePerKgOf(new BigDecimal(300))
            .withOrderType(OrderType.BUY)
            .build();
  }

  @Test
  public void cancellingOrderRemovesItFromDataStore() {
    cancelOrder.cancel(order);

    verify(dataStore).delete(order);
  }

  @Test
  public void handlesExceptionWhenDeletingErrors() {
    doThrow(new RuntimeException("Error for test")).when(dataStore).delete(order);

    assertThrows(
        CancelOrderException.class,
        () -> {
          cancelOrder.cancel(order);
        });
  }
}
