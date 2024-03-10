package dev.stephen.weatherapi.provider;

import dev.stephen.weatherapi.model.response.CityCoordinatesResponse;
import dev.stephen.weatherapi.model.response.WeatherResponse;
import lombok.RequiredArgsConstructor;
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

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenWeatherProvider {
    @Value("${open.weather.url}")
    private String openWeatherUrl;
    @Value("${geographic.api}")
    private String apikey;
    public WeatherResponse getCityWeather(String sessionId, CityCoordinatesResponse coordinates){
        log.info("[{}] about to request weather from open weather", sessionId);
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<WeatherResponse> weatherResponse;
        HttpEntity<String> requestEntity = new HttpEntity<>(null, null);
        log.info("[{}] building url for weather details", sessionId);
        UriComponents urlBuilder = UriComponentsBuilder.fromHttpUrl(openWeatherUrl)
                .queryParam("lat", coordinates.getLatitude())
                .queryParam("lon", coordinates.getLongitude())
                .queryParam("apikey", apikey)
                .build();

        try{
            weatherResponse = restTemplate.exchange(urlBuilder.toUriString(),
            HttpMethod.GET,
            requestEntity,
            WeatherResponse.class);
        } catch (HttpStatusCodeException e){
            log.info("[{}] error getting weather", sessionId);
            throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST){
            };
        }
        log.info("[{}] done getting weather response - {}", sessionId, weatherResponse);
        return weatherResponse.getBody();
    }
}
