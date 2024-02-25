package cz.utb.duck.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataService {

    public void getDate() {
        RestTemplate restTemplate = new RestTemplate();
        String apiKey = "SYLCILUIYRQCHRN0";
        String symbol = "AAPL"; // Example stock symbol

        String url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + apiKey;

        String jsonResponse = restTemplate.getForObject(url, String.class);

        System.out.println(jsonResponse);
    }
}
