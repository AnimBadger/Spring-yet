package dev.stephen.weatherapi.service;

import dev.stephen.weatherapi.converter.CityResponseToCoordinatesConverter;
import dev.stephen.weatherapi.model.response.CityCoordinatesResponse;
import dev.stephen.weatherapi.provider.GeocodingProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {
    private final GeocodingProvider geocodingProvider;
    private final CityResponseToCoordinatesConverter coordinatesConverter;
    public CityCoordinatesResponse getWeather(String sessionId, String city) throws Exception {
        log.info("[{}] service making request for {}", sessionId, city);
        // get coordinates for city
        coordinatesConverter.entityConverter(sessionId, geocodingProvider.getCoordinates(sessionId, city));
        return null;
    }
}
