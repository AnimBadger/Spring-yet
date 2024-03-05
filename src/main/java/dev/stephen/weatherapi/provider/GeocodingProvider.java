package dev.stephen.weatherapi.provider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dev.stephen.weatherapi.model.response.CityCoordinatesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class GeocodingProvider {
    @Value("${geographic.api}")
    private String apikey;

    @Value("${geocoding.url}")
    private String geocodingUrl;

    public static final int LIMIT = 1;
    public CityCoordinatesResponse getCoordinates(String sessionId, String city) throws Exception {
        log.info("[{}] about to make get request to geolocation api for {}", sessionId, city);
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<CityCoordinatesResponse[]> coordinates;

        HttpEntity<String> requestEntity = new HttpEntity<>(null, null);

        log.info("[{}] building URI", sessionId);
        UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(geocodingUrl)
                .queryParam("q", city)
                .queryParam("limit", LIMIT)
                .queryParam("appid", apikey)
                .build();

        try{
            coordinates = restTemplate.exchange(uriBuilder.toUriString(),
                    HttpMethod.GET,
                    requestEntity,
                    CityCoordinatesResponse[].class);
        } catch (HttpStatusCodeException e){
            log.info("[{}] error building uri", sessionId);
            throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST) {
            };
        }
        log.info("[{}] done building uri", sessionId);
        return Objects.requireNonNull(coordinates.getBody())[0];
    }
}
