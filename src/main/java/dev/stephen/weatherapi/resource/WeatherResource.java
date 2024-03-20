package dev.stephen.weatherapi.resource;
import dev.stephen.weatherapi.model.response.CityCoordinatesResponse;
import dev.stephen.weatherapi.model.response.WeatherResponse;
import dev.stephen.weatherapi.service.WeatherService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class WeatherResource {
    private final WeatherService weatherService;
    @GetMapping("weather/{city}")
    public ResponseEntity<WeatherResponse> getWeather(@PathVariable String city, HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        log.info("[{}] about to get weather for {} in service", sessionId, city);
        WeatherResponse response = weatherService.getWeather(sessionId, city);
        return ResponseEntity.ok(response);
    }
}
