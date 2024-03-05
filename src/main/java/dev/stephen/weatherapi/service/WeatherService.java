package dev.stephen.weatherapi.service;

import dev.stephen.weatherapi.converter.CityResponseToCoordinatesConverter;
import dev.stephen.weatherapi.model.response.CityCoordinatesResponse;
import dev.stephen.weatherapi.model.response.CityCoordinatesResponseEntity;
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
    private final CityResponseToCoordinatesConverter coordinatesConverter;
    private final OpenWeatherProvider openWeatherProvider;
    public WeatherResponse getWeather(String sessionId, String city) throws Exception {
        log.info("[{}] service making request for {}", sessionId, city);
        // get coordinates for city
        CityCoordinatesResponseEntity cityCoordinates = coordinatesConverter.entityConverter(sessionId, geocodingProvider.getCoordinates(sessionId, city));
        return openWeatherProvider.getCityWeather(sessionId, cityCoordinates);

    }
}
