package com.backendchallenge.iventis.api.client.impl;

import com.backendchallenge.iventis.api.client.MockDataClient;
import com.backendchallenge.iventis.api.client.response.CustomerClientResponse;
import com.backendchallenge.iventis.api.client.response.ProductClientResponse;
import com.backendchallenge.iventis.api.config.HttpClientGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public record MockDataClientImpl(
        @Value("${hosts.mock.url}")
        String hostUrl,
        @Value("${hosts.mock.products-path}")
        String productsPath,
        @Value("${hosts.mock.customers-path}")
        String customersPath,
        HttpClientGateway httpClientGateway
) implements MockDataClient {

    @Override
    public List<ProductClientResponse> listAllProducts() {
        var uri = createUri(productsPath);
        return httpClientGateway.get(uri, new ParameterizedTypeReference<>() {
        });
    }

    @Override
    public List<CustomerClientResponse> listAllCustomers() {
        var uri = createUri(customersPath);
        return httpClientGateway.get(uri, new ParameterizedTypeReference<>() {
        });
    }

    private String createUri(String path) {
        return UriComponentsBuilder
                .fromHttpUrl(hostUrl)
                .path(path)
                .build()
                .toUriString();
    }
}
