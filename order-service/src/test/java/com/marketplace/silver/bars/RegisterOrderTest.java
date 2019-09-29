package com.marketplace.silver.bars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.mockito.Mockito.*;

public class RegisterOrderTest {
  DataStore dataStore;
  private RegisterOrder registerOrder;

  @BeforeEach
  public void setup() {
    dataStore = mock(DataStore.class);
    registerOrder = new RegisterOrder(dataStore);
  }

  @Test
  public void registeringOrderAddsOrderToDataStore() {
    Order order = new Order("user1", 3.5f, new BigDecimal(303), OrderType.SELL);

    registerOrder.add(order);

    verify(dataStore).create(order);
  }

  @Test
  public void handlesExceptionWhenRegistrationErrors() {
    Order order = new Order("user1", 3.5f, new BigDecimal(303), OrderType.SELL);
    doThrow(new RuntimeException("Error for test")).when(dataStore).create(order);

    Assertions.assertThrows(RegisterOrderException.class, () -> {
      registerOrder.add(order);
    });
  }
}
