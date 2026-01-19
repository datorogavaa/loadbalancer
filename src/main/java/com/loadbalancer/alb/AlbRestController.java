package com.loadbalancer.alb;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


@Slf4j
@RestController
public class AlbRestController {

    private final WebClient webClient;
    private final ServerInterface serverInterface;

    public AlbRestController(WebClient webClient, ServerInterface serverInterface) {
        this.webClient = webClient;
        this.serverInterface = serverInterface;
    }

    @GetMapping("/")
    public String loadbalancer() {
        log.info("AlbRestController.loadbalancer()");
        return webClient.get().uri(serverInterface.Service())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }



}