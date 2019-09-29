package com.marketplace.silver.bars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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
            .withQuanityOf(3.5f)
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

    Assertions.assertThrows(
        RegisterOrderException.class,
        () -> {
          registerOrder.add(order);
        });
  }
}
