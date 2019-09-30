package com.marketplace.silver.bars.summary;

import com.marketplace.silver.bars.Order;
import com.marketplace.silver.bars.OrderType;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LiveBoardOrderFormatter {
  private static DecimalFormat decimalFormat = new DecimalFormat("#.##");
  private static final String SELL = "SELL: \n";
  private static final String BUY = "BUY: \n";

  public String summarise(List<Order> allOrders) {
    StringBuffer displaySellOffers =
        prepareToDisplay(
            allOrders, order -> OrderType.SELL.equals(order.getOrderType()), sortAscending);

    StringBuffer displayBuyOrders =
        prepareToDisplay(
            allOrders, order -> OrderType.BUY.equals(order.getOrderType()), sortDescending);

    return SELL + displaySellOffers.append(BUY).append(displayBuyOrders).toString();
  }

  private Function<OrderUnit, OrderUnit> sortAscending =
      (OrderUnit orders) -> new OrderUnit(new TreeMap<>(orders.getRawData()));

  private Function<OrderUnit, OrderUnit> sortDescending =
      (OrderUnit orders) -> {
        OrderUnit reverseSortedOrders = new OrderUnit(new TreeMap<>(Collections.reverseOrder()));
        reverseSortedOrders.putAll(orders);
        return reverseSortedOrders;
      };

  private StringBuffer prepareToDisplay(
      List<Order> allOrders,
      Predicate<Order> ordersWithSpecificType,
      Function<OrderUnit, OrderUnit> sortOrders) {
    Stream<Order> allSellOrdersStream = allOrders.stream().filter(ordersWithSpecificType);

    OrderUnit groupedSellOrders = groupOrdersByPrice(allSellOrdersStream);
    OrderUnit formattedSellOrders = sortOrders.apply(groupedSellOrders);

    return display(formattedSellOrders);
  }

  private OrderUnit groupOrdersByPrice(Stream<Order> orders) {
    return new OrderUnit(
        orders.collect(
            Collectors.groupingBy(
                Order::getPricePerKg, Collectors.summingDouble(Order::getQuantity))));
  }

  private StringBuffer display(OrderUnit allOrders) {
    StringBuffer output = new StringBuffer();
    allOrders.forEach(
        (price, quantity) -> {
          output
              .append(decimalFormat.format(quantity))
              .append("kg for £")
              .append(price)
              .append("\n");
        });
    return output;
  }
}
