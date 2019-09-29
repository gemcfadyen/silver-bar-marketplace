package com.marketplace.silver.bars.summary;

import com.marketplace.silver.bars.Order;
import com.marketplace.silver.bars.OrderType;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LiveBoardOrderFormatter {
  private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

  public String summarise(List<Order> allOrders) {
    Stream<Order> allSellOrdersStream =
        allOrders.stream().filter(order -> order.getOrderType() == OrderType.SELL);

    Map<BigDecimal, Double> groupedSellOrders = groupOrdersByPrice(allSellOrdersStream);
    Map<BigDecimal, Double> formattedSellOrders = formatSellOrders(groupedSellOrders);
    StringBuffer displayForSellOffers = display(formattedSellOrders);

    Stream<Order> allBuyOrdersStream =
        allOrders.stream().filter(order -> order.getOrderType() == OrderType.BUY);

    Map<BigDecimal, Double> groupedBuyOrders = groupOrdersByPrice(allBuyOrdersStream);
    Map<BigDecimal, Double> formattedBuyOrders = formatBuyOrders(groupedBuyOrders);
    StringBuffer displayForBuyOrders = display(formattedBuyOrders);

    return displayForSellOffers.append(displayForBuyOrders).toString();
  }

  private Map<BigDecimal, Double> groupOrdersByPrice(Stream<Order> orders) {
    return orders.collect(
        Collectors.groupingBy(Order::getPricePerKg, Collectors.summingDouble(Order::getQuantity)));
  }

  private Map<BigDecimal, Double> formatSellOrders(Map<BigDecimal, Double> orders) {
    return new TreeMap<>(orders);
  }

  private Map<BigDecimal, Double> formatBuyOrders(Map<BigDecimal, Double> orders) {
    Map<BigDecimal, Double> reverseSortedOrders = new TreeMap<>(Collections.reverseOrder());
    reverseSortedOrders.putAll(orders);
    return reverseSortedOrders;
  }

  private StringBuffer display(Map<BigDecimal, Double> allOrders) {
    StringBuffer output = new StringBuffer();
    allOrders.forEach(
        (price, quantity) -> {
          System.out.println("The price is " + price + " and the quantity is " + quantity);
          output
              .append(decimalFormat.format(quantity))
              .append("kg for £")
              .append(price)
              .append("\n");
        });
    return output;
  }
}
