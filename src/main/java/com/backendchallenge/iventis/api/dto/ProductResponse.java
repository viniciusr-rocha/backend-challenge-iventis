package com.backendchallenge.iventis.api.dto;

public record ProductResponse(
        Integer codigo,
        String tipoVinho,
        Double preco,
        String safra,
        String anoCompra
) {
}
