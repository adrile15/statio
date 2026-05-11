package com.statio.api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/geocoding")
@CrossOrigin
public class GeocodingController {

    @GetMapping("/search")
    public String searchLocation(@RequestParam String query){

        try {

            String encodedQuery =
                URLEncoder.encode(query, StandardCharsets.UTF_8);

            String url =
                "https://nominatim.openstreetmap.org/search?format=json&q="
                + encodedQuery;

            HttpHeaders headers = new HttpHeaders();

            headers.set("User-Agent", "StatioApp");

            HttpEntity<String> entity =
                new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response =
                restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class
                );

            return response.getBody();

        } catch (Exception e){

            e.printStackTrace();
            return "[]";
        }
    }

    @GetMapping("/reverse")
    public String reverseLocation(
            @RequestParam double lat,
            @RequestParam double lon){

        try {

            String url =
                "https://nominatim.openstreetmap.org/reverse?format=json&lat="
                + lat +
                "&lon=" +
                lon;

            HttpHeaders headers = new HttpHeaders();

            headers.set("User-Agent", "StatioApp");

            HttpEntity<String> entity =
                new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response =
                restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class
                );

            return response.getBody();

        } catch (Exception e){

            e.printStackTrace();
            return "{}";
        }
    }
}