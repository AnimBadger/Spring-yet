package dev.stephen.weatherapi.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityCoordinatesResponse {
    @JsonProperty("lon")
    private String longitude;
    @JsonProperty("lat")
    private  String latitude;
}
