package com.navi.domain;

import java.math.BigDecimal;

public class Trade {
    private Order buyOrder;
    private Order sellOrder;
    private int quantity;
    private BigDecimal price;

    public Trade(Order buyOrder, Order sellOrder, int quantity) {
        this.buyOrder = buyOrder;
        this.sellOrder = sellOrder;
        this.quantity = quantity;
        this.price = sellOrder.getPrice();
    }

    public int getBuyOrderId() {
        return buyOrder.getId();
    }

    public int getSellOrderId() {
        return sellOrder.getId();
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getSellPrice(){
        return price;
    }

    @Override
    public String toString() {
        return String.format("#%s %s %s #%s", getBuyOrderId(), getSellPrice(), getQuantity(), getSellOrderId());
    }
}
