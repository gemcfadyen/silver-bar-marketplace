package com.marketplace.silver.bars.datastore;

import com.marketplace.silver.bars.Order;
import com.marketplace.silver.bars.OrderType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.marketplace.silver.bars.OrderBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryDataStoreTest {
  private InMemoryDataStore inMemoryDataStore = new InMemoryDataStore();

  @Test
  public void addsOrderToStore() {
    Order order =
        anOrderBuilder()
            .withUserId("user1")
            .withQuanityOf(3.5f)
            .withPricePerKgOf(new BigDecimal(303))
            .withOrderType(OrderType.SELL)
            .build();

    inMemoryDataStore.create(order);

    List<Order> data = inMemoryDataStore.getDataStore();
    assertEquals(data.size(), 1);
    assertEquals(data.get(0), order);
  }

  @Test
  public void addsMultipleOrdersToStore() {
    Order order1 =
        anOrderBuilder()
            .withUserId("user1")
            .withQuanityOf(3.5f)
            .withPricePerKgOf(new BigDecimal(303))
            .withOrderType(OrderType.SELL)
            .build();

    Order order2 =
        anOrderBuilder()
            .withUserId("user2")
            .withQuanityOf(3.1f)
            .withPricePerKgOf(new BigDecimal(300))
            .withOrderType(OrderType.BUY)
            .build();

    inMemoryDataStore.create(order1);
    inMemoryDataStore.create(order2);

    List<Order> data = inMemoryDataStore.getDataStore();
    assertEquals(data.size(), 2);
    assertEquals(data.get(0), order1);
    assertEquals(data.get(1), order2);
  }

  @Test
  public void removesOrderFromStore() {
    Order order =
        anOrderBuilder()
            .withUserId("user1")
            .withQuanityOf(3.6f)
            .withPricePerKgOf(new BigDecimal(200))
            .withOrderType(OrderType.BUY)
            .build();

    inMemoryDataStore.create(order);
    inMemoryDataStore.delete(order);

    List<Order> data = inMemoryDataStore.getDataStore();
    assertEquals(data.size(), 0);
  }

  @Test
  public void removesOnlySelectedOrderFromStore() {
    Order order1 =
        anOrderBuilder()
            .withUserId("user1")
            .withQuanityOf(3.6f)
            .withPricePerKgOf(new BigDecimal(200))
            .withOrderType(OrderType.BUY)
            .build();

    Order order2 =
        anOrderBuilder()
            .withUserId("user2")
            .withQuanityOf(3.1f)
            .withPricePerKgOf(new BigDecimal(209))
            .withOrderType(OrderType.SELL)
            .build();

    inMemoryDataStore.create(order1);
    inMemoryDataStore.create(order2);

    inMemoryDataStore.delete(order2);

    List<Order> data = inMemoryDataStore.getDataStore();
    assertEquals(data.size(), 1);
    assertEquals(data.get(0), order1);
  }

  @Test
  public void doesNotRemoveWhenNoMatchingRegisteredOrder() {
    Order order1 =
        anOrderBuilder()
            .withUserId("user1")
            .withQuanityOf(3.6f)
            .withPricePerKgOf(new BigDecimal(200))
            .withOrderType(OrderType.BUY)
            .build();

    inMemoryDataStore.create(order1);

    inMemoryDataStore.delete(anOrderBuilder().withUserId("user2").build());

    List<Order> data = inMemoryDataStore.getDataStore();
    assertEquals(data.size(), 1);
    assertEquals(data.get(0), order1);
  }
}
