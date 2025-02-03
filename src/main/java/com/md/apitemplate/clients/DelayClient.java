package com.md.apitemplate.clients;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class DelayClient {

    private final WebClient.Builder webClientBuilder;

    private WebClient webClient;

    @Value("${delay.url}")
    private String delayUrl;

    @Value("${delay.seconds}")
    private Integer delaySeconds;

    public DelayClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostConstruct
    public void init() {
        this.webClient = webClientBuilder.baseUrl(delayUrl).build();
    }

    public String getDelay() {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/delay")
                        .queryParam("inSeconds", delaySeconds)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}