package com.techelevator.services;

import com.techelevator.model.StockWrapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.*;
;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class YahooService {
//    private String baseURL = "https://yfapi.net";

    RestTemplate restTemplate = new RestTemplate();

    public YahooService() {

    }

    public List<String> listTrendingStocks() throws IOException, InterruptedException {

        HttpEntity<String> entity = new HttpEntity<>(makeHeader("x-api-key", "XBVQM29pipd4zorvSlvi3uFEeCyeJae9AeXfktM7"));
        String[] allSymbols = restTemplate.exchange("https://yfapi.net/v1/finance/trending/US", HttpMethod.GET,entity,String[].class).getBody();
        return Arrays.asList(allSymbols);


//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://yfapi.net/v1/finance/trending/US"))
//                .header("x-api-key", "XBVQM29pipd4zorvSlvi3uFEeCyeJae9AeXfktM7")
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient()
//                .send(request, HttpResponse.BodyHandlers.ofString();
//        return response.body();

    }

    private org.springframework.http.HttpHeaders makeHeader(String username, String token) {
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setBasicAuth(username,token);
        return headers;
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
