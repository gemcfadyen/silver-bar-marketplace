package com.marketplace.silver.bars.register;

import com.marketplace.silver.bars.DataStore;
import com.marketplace.silver.bars.Order;
import com.marketplace.silver.bars.OrderBuilder;
import com.marketplace.silver.bars.OrderType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegisterOrderTest {
  private DataStore dataStore;
  private RegisterOrder registerOrder;
  private Order order;

  @BeforeEach
  public void setup() {
    dataStore = mock(DataStore.class);
    registerOrder = new RegisterOrder(dataStore);
    order =
        OrderBuilder.anOrderBuilder()
            .withUserId("user1")
            .withQuantityOf(3.5)
            .withPricePerKgOf(new BigDecimal(303))
            .withOrderType(OrderType.SELL)
            .build();
  }

  @Test
  public void registeringOrderAddsOrderToDataStore() {
    registerOrder.add(order);

    verify(dataStore).create(order);
  }

  @Test
  public void handlesExceptionWhenRegistrationErrors() {
    doThrow(new RuntimeException("Error for test")).when(dataStore).create(order);

    assertThrows(
        RegisterOrderException.class,
        () -> {
          registerOrder.add(order);
        });
  }
}
