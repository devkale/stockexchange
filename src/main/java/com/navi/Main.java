package com.navi;
import com.navi.client.FileClient;
import com.navi.services.StockExchange;

public class Main {
    public static void main(String[] args) {

        if(args.length!=0) {
            FileClient client = new FileClient(new StockExchange());
            client.readFile(args[0]);
        }
    }
}
