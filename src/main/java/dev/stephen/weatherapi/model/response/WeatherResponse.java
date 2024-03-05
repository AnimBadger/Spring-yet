package dev.stephen.weatherapi.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherResponse {
    @JsonProperty("weather")
    private WeatherEntity[] weather;
    @JsonProperty("main")
    private MainEntity main;

}
