package com.navi.services;

import com.navi.domain.Order;
import com.navi.domain.OrderType;
import com.navi.domain.Stock;
import com.navi.domain.Trade;

import java.util.*;

public class StockExchange {
    private List<Order> orders;
    private List<Trade> trades;
    private Map<String, Stock> stocks;

    public StockExchange() {
        this.orders = new ArrayList<>();
        this.trades = new ArrayList<>();
        this.stocks = new HashMap<>();
        populateStocks();
    }

    private void populateStocks() {
        stocks.put("BAC", new Stock("BAC"));
    }

    public void addOrder(Order order) {
        orders.add(order);
        match();
    }

    private void match() {
        for (Order order: orders) {
            if (order.getType() == OrderType.BUY && order.getBalanceQuantity() !=0) {
                for(Order sellOrder : orders) {
                    if(order.getStock() == sellOrder.getStock() && sellOrder.getType() == OrderType.SELL && sellOrder.getBalanceQuantity()!=0) {
                        if(order.getPrice().compareTo(sellOrder.getPrice())>=0) {
                            int min = Math.min(order.getBalanceQuantity(), sellOrder.getBalanceQuantity());
                            order.reduceQuantityBy(min);
                            sellOrder.reduceQuantityBy(min);
                            Trade trade = new Trade(order, sellOrder,min);
                            trades.add(trade);
                        }
                    }
                }
            }
        }
    }

    public List<Trade>  getTrades(){
        return trades;
    }

    public Stock getStock(String symbol) {
        if(stocks.get(symbol) == null) {
            stocks.put(symbol, new Stock(symbol));
        }

        return stocks.get(symbol);
    }

}
