package com.backendchallenge.iventis.api.dto;

import com.backendchallenge.iventis.api.client.response.ProductClientResponse;

public record ProductResponse(
        Integer codigo,
        String tipoVinho,
        Double preco,
        String safra,
        String anoCompra
) {
    public static ProductResponse of(ProductClientResponse productClientResponse) {
        return new ProductResponse(
                productClientResponse.codigo(),
                productClientResponse.tipoVinho(),
                productClientResponse.preco(),
                productClientResponse.safra(),
                productClientResponse.anoCompra()
        );
    }
}
