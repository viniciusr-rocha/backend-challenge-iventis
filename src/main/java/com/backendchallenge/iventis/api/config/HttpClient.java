package com.backendchallenge.iventis.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

@Configuration
public class HttpClient implements HttpClientGateway {
    private final RestClient restClient;

    public HttpClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public <T> List<T> get(String uri, ParameterizedTypeReference<List<T>> bodyType) {
        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(bodyType);
    }
}
