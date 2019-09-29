package com.marketplace.silver.bars.summary;

import com.marketplace.silver.bars.Order;
import com.marketplace.silver.bars.OrderType;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LiveBoardOrderFormatter {
  private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

  public String summarise(List<Order> allOrders) {
    StringBuffer displaySellOffers =
        prepareToDisplay(
            allOrders, order -> OrderType.SELL.equals(order.getOrderType()), sortAscending);

    StringBuffer displayBuyOrders =
        prepareToDisplay(
            allOrders, order -> OrderType.BUY.equals(order.getOrderType()), sortDescending);

    return displaySellOffers.append(displayBuyOrders).toString();
  }

  private Function<Map<BigDecimal, Double>, Map<BigDecimal, Double>> sortAscending = TreeMap::new;

  private Function<Map<BigDecimal, Double>, Map<BigDecimal, Double>> sortDescending =
      (Map<BigDecimal, Double> orders) -> {
        Map<BigDecimal, Double> reverseSortedOrders = new TreeMap<>(Collections.reverseOrder());
        reverseSortedOrders.putAll(orders);
        return reverseSortedOrders;
      };

  private StringBuffer prepareToDisplay(
      List<Order> allOrders,
      Predicate<Order> ordersWithSpecificType,
      Function<Map<BigDecimal, Double>, Map<BigDecimal, Double>> sortOrders) {
    Stream<Order> allSellOrdersStream = allOrders.stream().filter(ordersWithSpecificType::test);

    Map<BigDecimal, Double> groupedSellOrders = groupOrdersByPrice(allSellOrdersStream);
    Map<BigDecimal, Double> formattedSellOrders = sortOrders.apply(groupedSellOrders);

    return display(formattedSellOrders);
  }

  private Map<BigDecimal, Double> groupOrdersByPrice(Stream<Order> orders) {
    return orders.collect(
        Collectors.groupingBy(Order::getPricePerKg, Collectors.summingDouble(Order::getQuantity)));
  }

  private StringBuffer display(Map<BigDecimal, Double> allOrders) {
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
