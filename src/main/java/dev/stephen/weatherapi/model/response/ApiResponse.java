package dev.stephen.weatherapi.model.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse {
    String message;
    HttpStatus status;
}
