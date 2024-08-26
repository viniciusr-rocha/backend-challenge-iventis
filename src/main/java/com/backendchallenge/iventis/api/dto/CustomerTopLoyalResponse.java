package com.backendchallenge.iventis.api.dto;

import com.backendchallenge.iventis.api.client.response.CustomerClientResponse;

public record CustomerTopLoyalResponse(
        String nome,
        String cpf,
        Integer totalCompras,
        Double valorTotalCompras
) {
    public static CustomerTopLoyalResponse of(
            CustomerClientResponse customer,
            double totalAmount
    ) {
        return new CustomerTopLoyalResponse(
                customer.nome(),
                customer.cpf(),
                customer.compras().size(),
                totalAmount
        );
    }
}
