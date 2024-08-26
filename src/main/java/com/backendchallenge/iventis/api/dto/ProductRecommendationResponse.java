package com.backendchallenge.iventis.api.dto;

public record ProductRecommendationResponse(
        String tipoVinho,
        Double preco,
        String safra,
        String anoCompra
) {
    public static ProductRecommendationResponse of(PurchaseDetailsResponse purchaseDetailsResponse) {
        var product = purchaseDetailsResponse.produto();
        return new ProductRecommendationResponse(
                product.tipoVinho(),
                product.preco(),
                product.safra(),
                product.anoCompra()
        );
    }
}
