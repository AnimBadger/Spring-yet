package dev.stephen.weatherapi.service;

import dev.stephen.weatherapi.model.response.CityCoordinatesResponse;
import dev.stephen.weatherapi.model.response.WeatherResponse;
import dev.stephen.weatherapi.provider.GeocodingProvider;
import dev.stephen.weatherapi.provider.OpenWeatherProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {
    private final GeocodingProvider geocodingProvider;
    private final OpenWeatherProvider openWeatherProvider;
    public WeatherResponse getWeather(String sessionId, String city) {
        log.info("[{}] service making request for coordinates of {}", sessionId, city);
        // get coordinates for city
        CityCoordinatesResponse cityCoordinates = geocodingProvider.getCoordinates(sessionId, city);
        // get city weather
        return openWeatherProvider.getCityWeather(sessionId, cityCoordinates);

    }
}
