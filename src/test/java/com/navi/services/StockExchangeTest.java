package com.navi.services;

import com.navi.domain.Order;
import com.navi.domain.OrderType;
import com.navi.domain.Stock;
import com.navi.domain.Trade;
import org.junit.Test;
import org.junit.Assert;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;


public class StockExchangeTest {

    @Test
    public void addOrder() {
        StockExchange stockExchange = StockExchange.getInstance();
        Stock stock = stockExchange.getStock("BAC");
        stockExchange.addOrder(new Order(1, LocalTime.of(9, 45),stock, OrderType.SELL, new BigDecimal("240.12"),100));
        stockExchange.addOrder(new Order(2, LocalTime.of(9, 46),stock, OrderType.SELL, new BigDecimal("237.45"),90));
        stockExchange.addOrder(new Order(3, LocalTime.of(9, 47),stock, OrderType.BUY, new BigDecimal("238.10"),110));
        stockExchange.addOrder(new Order(4, LocalTime.of(9, 48),stock, OrderType.BUY, new BigDecimal("237.80"),10));
        stockExchange.addOrder(new Order(5, LocalTime.of(9, 49),stock, OrderType.BUY, new BigDecimal("237.80"),40));
        stockExchange.addOrder(new Order(6, LocalTime.of(9, 50),stock, OrderType.SELL, new BigDecimal("236.00"),50));

        List<Trade> trades = stockExchange.getTrades();
        Assert.assertEquals(trades.get(0).toString(), "#3 237.45 90 #2");
        Assert.assertEquals(trades.get(1).toString(), "#3 236.00 20 #6");
        Assert.assertEquals(trades.get(2).toString(), "#4 236.00 10 #6");
        Assert.assertEquals(trades.get(3).toString(), "#5 236.00 20 #6");
    }


}