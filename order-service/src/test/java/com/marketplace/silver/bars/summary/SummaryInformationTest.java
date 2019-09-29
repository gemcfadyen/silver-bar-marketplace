package com.marketplace.silver.bars.summary;

import com.marketplace.silver.bars.DataStore;
import com.marketplace.silver.bars.Order;
import com.marketplace.silver.bars.OrderBuilder;
import com.marketplace.silver.bars.OrderType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SummaryInformationTest {
  private DataStore dataStore;
  private OrderSummary orderSummary;
  private LiveBoardOrderFormatter liveBoardOrderFormatter;

  @BeforeEach
  public void setup() {
    dataStore = mock(DataStore.class);
    liveBoardOrderFormatter = mock(LiveBoardOrderFormatter.class);
    orderSummary = new OrderSummary(dataStore, liveBoardOrderFormatter);
  }

  @Test
  public void fetchesAllOrdersFromDataStore() {
    orderSummary.display();

    verify(dataStore).getAll();
  }

  @Test
  public void formatsOrderDataForDisplay() {
    List<Order> allOrders = Arrays.asList(buildOrderWithPriceOf(new BigDecimal(102)));
    when(dataStore.getAll()).thenReturn(allOrders);

    orderSummary.display();

    verify(liveBoardOrderFormatter).summarise(allOrders);
  }

  @Test
  public void returnsFormattedData() {
    List<Order> allOrders = Arrays.asList(buildOrderWithPriceOf(new BigDecimal(102)));
    when(dataStore.getAll()).thenReturn(allOrders);
    when(liveBoardOrderFormatter.summarise(allOrders)).thenReturn("Summarised Data");

    String output = orderSummary.display();

    assertEquals(output, "Summarised Data");
  }

  private Order buildOrderWithPriceOf(BigDecimal price) {
    return OrderBuilder.anOrderBuilder()
        .withUserId("user1")
        .withQuantityOf(3.6)
        .withPricePerKgOf(price)
        .withOrderType(OrderType.SELL)
        .build();
  }
}
