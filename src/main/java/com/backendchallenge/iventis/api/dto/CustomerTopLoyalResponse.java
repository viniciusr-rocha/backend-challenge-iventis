package com.backendchallenge.iventis.api.dto;

public record CustomerTopLoyalResponse(
        String nome,
        String cpf,
        Integer totalCompras,
        Double valorTotalCompras
) {
}
