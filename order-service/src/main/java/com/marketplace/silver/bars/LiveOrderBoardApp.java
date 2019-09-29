package com.marketplace.silver.bars;

import com.marketplace.silver.bars.cancel.CancelOrder;
import com.marketplace.silver.bars.datastore.InMemoryDataStore;
import com.marketplace.silver.bars.display.Display;
import com.marketplace.silver.bars.register.RegisterOrder;
import com.marketplace.silver.bars.summary.LiveBoardOrderFormatter;
import com.marketplace.silver.bars.summary.OrderSummary;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;

public class LiveOrderBoardApp {

  public static void main(String... args) {
    DataStore dataStore = new InMemoryDataStore();
    RegisterOrder registerOrder = new RegisterOrder(dataStore);
    CancelOrder cancelOrder = new CancelOrder(dataStore);
    OrderSummary orderSummary = new OrderSummary(dataStore, new LiveBoardOrderFormatter());
    Display display = new Display(new BufferedWriter(new OutputStreamWriter(System.out)));

    LiveOrderBoard liveOrderBoard = new LiveOrderBoard(registerOrder, cancelOrder, orderSummary, display);

    Order order1 =
        OrderBuilder.anOrderBuilder()
            .withUserId("user1")
            .withPricePerKgOf(new BigDecimal(130))
            .withQuantityOf(5)
            .withOrderType(OrderType.BUY)
            .build();

    liveOrderBoard.register(order1);

    Order order2 =
        OrderBuilder.anOrderBuilder()
            .withUserId("user2")
            .withPricePerKgOf(new BigDecimal(160))
            .withQuantityOf(3.5)
            .withOrderType(OrderType.SELL)
            .build();

    liveOrderBoard.register(order2);

    Order order3 =
        OrderBuilder.anOrderBuilder()
            .withUserId("user1")
            .withPricePerKgOf(new BigDecimal(100))
            .withQuantityOf(4.3)
            .withOrderType(OrderType.BUY)
            .build();

    liveOrderBoard.register(order3);
    liveOrderBoard.cancel(order1);

    Order order4 =
        OrderBuilder.anOrderBuilder()
            .withUserId("user3")
            .withPricePerKgOf(new BigDecimal(210))
            .withQuantityOf(3.1)
            .withOrderType(OrderType.SELL)
            .build();

    liveOrderBoard.register(order4);

    Order order5 =
        OrderBuilder.anOrderBuilder()
            .withUserId("user4")
            .withPricePerKgOf(new BigDecimal(210))
            .withQuantityOf(2.1)
            .withOrderType(OrderType.SELL)
            .build();

    liveOrderBoard.register(order5);

    Order order6 =
            OrderBuilder.anOrderBuilder()
                    .withUserId("user6")
                    .withPricePerKgOf(new BigDecimal(120))
                    .withQuantityOf(2)
                    .withOrderType(OrderType.BUY)
                    .build();

    liveOrderBoard.register(order6);

    liveOrderBoard.summarise();
  }
}
