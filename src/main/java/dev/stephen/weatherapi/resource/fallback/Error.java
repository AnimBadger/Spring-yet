package dev.stephen.weatherapi.resource.fallback;

import dev.stephen.weatherapi.model.response.ApiResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
class Error implements ErrorController {
    @RequestMapping("/error")
    public ResponseEntity<ApiResponse> fallBackError() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Time out");
        apiResponse.setStatus(HttpStatus.GATEWAY_TIMEOUT);
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(apiResponse);
    }
}
