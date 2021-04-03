package com.navi.services;

import com.navi.domain.Order;
import com.navi.domain.OrderType;
import com.navi.domain.Stock;
import com.navi.domain.Trade;

import java.util.*;

public class StockExchange {
    private List<Order> buyOrders;
    private List<Order> sellOrders;
    private List<Trade> trades;
    private Map<String, Stock> stocks;
    private static StockExchange instance = null;

    private StockExchange() {
        this.buyOrders = new ArrayList<>();
        this.sellOrders = new ArrayList<>();
        this.trades = new ArrayList<>();
        this.stocks = new HashMap<>();
    }

    public synchronized static StockExchange getInstance() {
        if(instance == null) {
            instance = new StockExchange();
        }
        return instance;
    }

    public void addOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException();
        }
        // TODO duplicate verification
        if(order.getType() == OrderType.BUY) {
            buyOrders.add(order);
        } else {
            sellOrders.add(order);
        }
        match();
    }

    private void match() {
        for (Order order: buyOrders) {
            if (order.getBalanceQuantity() !=0) {
                for(Order sellOrder : sellOrders) {
                    if(order.getStock() == sellOrder.getStock() && sellOrder.getBalanceQuantity()!=0) {
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
