package com.navi.domain;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Order {
    protected Stock stock;
    protected int quantity;
    protected BigDecimal price;
    protected LocalTime time;
    protected int id;
    protected OrderType type;
    protected int balanceQuantity;

    public Order(int id, LocalTime time, Stock stock, OrderType type, BigDecimal price, int quantity) {
        this.stock = stock;
        this.quantity = quantity;
        this.balanceQuantity = quantity;
        this.type = type;
        this.price = price;
        this.time = time;
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public OrderType getType() {
        return type;
    }

    public int getBalanceQuantity() {
        return balanceQuantity;
    }

    public void reduceQuantityBy(int quantity) {
        this.balanceQuantity -= quantity;
    }


}
