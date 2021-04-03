package com.navi;
import com.navi.client.FileClient;
import com.navi.services.StockExchange;

public class Main {
    public static void main(String[] args) {
        if(args.length == 1) {
            FileClient client = new FileClient(StockExchange.getInstance());
            client.readFile(args[0]);
        }
    }
}
