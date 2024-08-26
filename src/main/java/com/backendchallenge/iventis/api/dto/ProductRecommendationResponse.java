package com.backendchallenge.iventis.api.dto;

public record ProductRecommendationResponse(
        String tipoVinho,
        Double preco,
        String safra,
        String anoCompra
) {
}
