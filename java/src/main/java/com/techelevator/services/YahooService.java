package com.techelevator.services;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import yahoofinance.quotes.stock.StockStats;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



@Service
public class YahooService {
//    private String baseURL = "https://yfapi.net";

    RestTemplate restTemplate = new RestTemplate();

    public YahooService() {

    }

    public String listTrendingStocks() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://yfapi.net/v1/finance/trending/US"))
                .header("x-api-key", "XBVQM29pipd4zorvSlvi3uFEeCyeJae9AeXfktM7")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();

    }

    public String getStockSummary(String symbol) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://yfapi.net/v11/finance/quoteSummary/" + symbol))
                .header("x-api-key", "XBVQM29pipd4zorvSlvi3uFEeCyeJae9AeXfktM7")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    //****************REFERENCE*****************************
//    HttpRequest request = HttpRequest.newBuilder()
//            .uri(URI.create("https://yfapi.net/v8/finance/chart/AAPL"))
//            .header("x-api-key", "YOUR-PERSONAL-API-KEY")
//            .method("GET", HttpRequest.BodyPublishers.noBody())
//            .build();
//    HttpResponse<String> response = HttpClient.newHttpClient()
//            .send(request, HttpResponse.BodyHandlers.ofString());
//System.out.println(response.body());

}
