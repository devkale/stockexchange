package com.navi.client;

import com.navi.domain.Order;
import com.navi.domain.OrderType;
import com.navi.domain.Stock;
import com.navi.domain.Trade;
import com.navi.services.StockExchange;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class FileClient {
    private StockExchange stockExchange;

    public FileClient(StockExchange stockExchange) {
        this.stockExchange = stockExchange;
    }

    public void readFile(String path) {
        List<Order> orders = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                String lineInput = myReader.nextLine();
                Order order = parseLine(lineInput);
                orders.add(order);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Invalid Filepath");
        }
        for (Order order :
                orders) {
            stockExchange.addOrder(order);
        }

        List<Trade> trades = stockExchange.getTrades();
        for (Trade trade :
                trades) {
            System.out.println(trade);
        }
    }

    public Order parseLine(String line) {
        String[] parts  = line.split("\\s+");
        if(parts.length < 6) {
            throw new IllegalArgumentException();
        }
        int id = Integer.parseInt(parts[0].substring(1));
        String[] time = parts[1].split(":");
        LocalTime  orderTime = LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
        Stock stock = stockExchange.getStock(parts[2]);
        if (stock == null) {
            throw new IllegalArgumentException("Invalid stock symbol.");
        }
        OrderType orderType = OrderType.valueOf(parts[3].toUpperCase(Locale.ROOT));
        BigDecimal price = new BigDecimal(parts[4]);
        int quantity = Integer.parseInt(parts[5]);
        return new Order(id, orderTime, stock, orderType, price, quantity);
    }

}
/*
#1 09:45 BAC sell 240.12 100
        #2 09:46 BAC sell 237.45  90
        #3 09:47 BAC buy  238.10 110
        #4 09:48 BAC buy  237.80  10
        #5 09:49 BAC buy  237.80  40
        #6 09:50 BAC sell 236.00  50
*/