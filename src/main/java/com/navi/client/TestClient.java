package com.navi.client;

import com.navi.domain.Order;
import com.navi.domain.Stock;
import com.navi.domain.Trade;
import com.navi.services.StockExchange;
import com.navi.domain.OrderType;

import java.math.BigDecimal;
import java.time.LocalTime;

public class TestClient {
    public static void main(String[] args) {
        StockExchange stockExchange = new StockExchange();
        Stock stock = new Stock("BAC");
        stockExchange.addOrder(new Order(1, LocalTime.of(9, 45),stock, OrderType.SELL, new BigDecimal("240.12"),100));
        stockExchange.addOrder(new Order(2, LocalTime.of(9, 46),stock, OrderType.SELL, new BigDecimal("237.45"),90));
        stockExchange.addOrder(new Order(3, LocalTime.of(9, 47),stock, OrderType.BUY, new BigDecimal("238.10"),110));
        stockExchange.addOrder(new Order(4, LocalTime.of(9, 48),stock, OrderType.BUY, new BigDecimal("237.80"),10));
        stockExchange.addOrder(new Order(5, LocalTime.of(9, 49),stock, OrderType.BUY, new BigDecimal("237.80"),40));
        stockExchange.addOrder(new Order(6, LocalTime.of(9, 50),stock, OrderType.SELL, new BigDecimal("236.00"),50));
        for(Trade trade:stockExchange.getTrades()) {
            System.out.println(trade);
        }
    }
}
/*
#1 09:45 BAC sell 240.12 100
#2 09:46 BAC sell 237.45  90
#3 09:47 BAC buy  238.10 110
#4 09:48 BAC buy  237.80  10
#5 09:49 BAC buy  237.80  40
#6 09:50 BAC sell 236.00  50
* */