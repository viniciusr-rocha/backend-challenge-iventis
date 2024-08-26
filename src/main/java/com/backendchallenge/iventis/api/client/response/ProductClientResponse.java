package com.backendchallenge.iventis.api.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductClientResponse(
        Integer codigo,
        @JsonProperty("tipo_vinho")
        String tipoVinho,
        Double preco,
        String safra,
        @JsonProperty("ano_compra")
        String anoCompra
) {
}
