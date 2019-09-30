package com.marketplace.silver.bars.summary;

import com.marketplace.silver.bars.Order;
import com.marketplace.silver.bars.OrderType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.marketplace.silver.bars.OrderBuilder.*;
import static com.marketplace.silver.bars.OrderType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LiveBoardOrderFormatterTest {
  private LiveBoardOrderFormatter liveBoardOrderFormatter = new LiveBoardOrderFormatter();

  @Test
  public void sumsOrdersOfSamePrice() {
    List<Order> allOrders =
        Arrays.asList(
            buildOrderWithPriceOf200(SELL, "user1", 2.3),
            buildOrderWithPriceOf200(SELL, "user2", 3.9));

    String output = liveBoardOrderFormatter.summarise(allOrders);

    assertEquals(output, "SELL: \n6.2kg for £200\nBUY: \n");
  }

  @Test
  public void sumsOrdersOfSamePriceAndDisplaysOtherOrders() {
    List<Order> allOrders =
        Arrays.asList(
            buildOrderWithPriceOf200(SELL, "user1", 2.3),
            buildOrderWithPriceOf150(SELL, "user1", 1.4),
            buildOrderWithPriceOf200(SELL, "user2", 3.9));

    String output = liveBoardOrderFormatter.summarise(allOrders);

    assertEquals(output, "SELL: \n1.4kg for £150\n6.2kg for £200\nBUY: \n");
  }

  @Test
  public void sellOrdersDisplayedInAscendingOrder() {
    List<Order> allOrders =
        Arrays.asList(
            buildOrderWithPriceOf200(SELL, "user1", 2.3),
            buildOrderWithPriceOf150(SELL, "user2", 1.4),
            buildOrderWithPriceOf200(SELL, "user3", 3.9),
            buildOrderWithPriceOf150(SELL, "user4", 1.5));

    String output = liveBoardOrderFormatter.summarise(allOrders);

    assertEquals(output, "SELL: \n2.9kg for £150\n6.2kg for £200\nBUY: \n");
  }

  @Test
  public void buyOrdersDisplayedInDescendingOrder() {
    List<Order> allOrders =
        Arrays.asList(
            buildOrderWithPriceOf200(BUY, "user1", 2.3),
            buildOrderWithPriceOf150(BUY, "user2", 1.4),
            buildOrderWithPriceOf200(BUY, "user3", 3.9),
            buildOrderWithPriceOf150(BUY, "user4", 1.5));

    String output = liveBoardOrderFormatter.summarise(allOrders);

    assertEquals(output, "SELL: \nBUY: \n6.2kg for £200\n2.9kg for £150\n");
  }

  @Test
  public void handlesFormattingWhenNoOrdersExist() {
    List<Order> noOrders = Collections.EMPTY_LIST;

    String output = liveBoardOrderFormatter.summarise(noOrders);

    assertEquals(output, "SELL: \nBUY: \n");
  }

  private Order buildOrderWithPriceOf200(OrderType orderType, String userId, double quantity) {
    return anOrderBuilder()
        .withUserId(userId)
        .withQuantityOf(quantity)
        .withPricePerKgOf(new BigDecimal(200))
        .withOrderType(orderType)
        .build();
  }

  private Order buildOrderWithPriceOf150(OrderType orderType, String userId, double quantity) {
    return anOrderBuilder()
        .withUserId(userId)
        .withQuantityOf(quantity)
        .withPricePerKgOf(new BigDecimal(150))
        .withOrderType(orderType)
        .build();
  }
}
