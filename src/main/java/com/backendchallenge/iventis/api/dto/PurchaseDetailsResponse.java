package com.backendchallenge.iventis.api.dto;

public record PurchaseDetailsResponse(
        String nome,
        String cpf,
        ProductResponse produto,
        Integer quantidade,
        Double valorTotal
) {
}
