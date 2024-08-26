package com.backendchallenge.iventis.api.config;

import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public interface HttpClientGateway {
    <T> List<T> get(String uri, ParameterizedTypeReference<List<T>> bodyType);
}
