package dev.stephen.weatherapi.converter;

import dev.stephen.weatherapi.model.response.CityCoordinatesResponse;
import dev.stephen.weatherapi.model.response.CityCoordinatesResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CityResponseToCoordinatesConverter {
    public CityCoordinatesResponseEntity entityConverter(String sessionId,CityCoordinatesResponse response){
        log.info("[{}] about to covert response to domain", sessionId);
        return CityCoordinatesResponseEntity.builder()
                .latitude(response.getLatitude())
                .longitude(response.getLongitude())
                .build();
    }
}
