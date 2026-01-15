package com.loadbalancer.alb;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;


@Slf4j
@RestController
public class albRestController {
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> home(HttpServletRequest request) {
        log.info("Request received at / endpoint");
        ResponseEntity<String> response =  ResponseEntity.status(HttpStatus.OK).body("OK");
        HttpStatus statusCode = (HttpStatus) response.getStatusCode();
        return ResponseEntity.ok(
                Map.of(
                        "serverPort", String.valueOf(request.getLocalPort()),
                        "User-Agent", Objects.requireNonNullElse(request.getHeader("User-Agent"), "Unknown"),
                        "clientIP", " Recieved Request from " + Objects.requireNonNullElse(request.getRemoteAddr(),"Unknown"),
                        "Accept", Objects.requireNonNullElse(request.getHeader("Accept"),"Unknown"),
                        "GET /", " Request Method is " + request.getMethod(),
                        "statusCode", " Response Status Code is " + statusCode.value(),
                        "message", " Hello from ALB! "
        ));

    }
}
