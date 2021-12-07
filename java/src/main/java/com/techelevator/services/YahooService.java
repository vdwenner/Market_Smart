package com.techelevator.services;

import com.techelevator.model.StockWrapper;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import yahoofinance.YahooFinance;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.*;
;

import java.util.ArrayList;
import java.util.List;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class YahooService {
    private String baseURL = "https://yfapi.net";

    RestTemplate restTemplate = new RestTemplate();

    public YahooService(String baseURL) {
        this.baseURL = baseURL;
    }

//    public List<StockWrapper> listTrendingStocks() {
//        List<StockWrapper> result = new ArrayList<>();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(baseURL + "v1/finance/trending/US"))
//                .header("x-api-key", "XBVQM29pipd4zorvSlvi3uFEeCyeJae9AeXfktM7")
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<List<StockWrapper>> response = HttpClient.newHttpClient()
//                .send(request, HttpResponse.BodyHandlers.ofString());
//        return result;

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
//}
