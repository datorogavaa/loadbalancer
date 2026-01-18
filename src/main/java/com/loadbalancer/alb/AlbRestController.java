package com.loadbalancer.alb;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


@Slf4j
@RestController
public class AlbRestController {

    private final WebClient webClient;
    private final ServerListService serverListService;

    public AlbRestController(WebClient webClient, ServerListService serverListService) {
        this.webClient = webClient;
        this.serverListService = serverListService;
    }





    @GetMapping("/")
    public String loadbalancer() {
        log.info("AlbRestController.loadbalancer()");
        String serverUrl = serverListService.serverGet();
        serverListService.addServer(serverUrl);
        return webClient.get().uri(serverUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }



}



//        log.info("Request received at / endpoint");
//        ResponseEntity<String> response =  ResponseEntity.status(HttpStatus.OK).body("OK");
//        HttpStatus statusCode = (HttpStatus) response.getStatusCode();
//        return ResponseEntity.ok(
//                Map.of(
//                        "serverPort", String.valueOf(request.getLocalPort()),
//                        "User-Agent", Objects.requireNonNullElse(request.getHeader("User-Agent"), "Unknown"),
//                        "clientIP", " Recieved Request from " + Objects.requireNonNullElse(request.getRemoteAddr(),"Unknown"),
//                        "Accept", Objects.requireNonNullElse(request.getHeader("Accept"),"Unknown"),
//                        "GET /", " Request Method is " + request.getMethod(),
//                        "statusCode", " Response Status Code is " + statusCode.value(),
//                        "message", " Hello from ALB! "
//        ));