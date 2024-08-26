package com.backendchallenge.iventis.api.dto;

import com.backendchallenge.iventis.api.client.response.CustomerClientResponse;
import com.backendchallenge.iventis.api.client.response.ProductClientResponse;
import com.backendchallenge.iventis.api.client.response.PurchaseClientResponse;

public record PurchaseDetailsResponse(
        String nome,
        String cpf,
        ProductResponse produto,
        Integer quantidade,
        Double valorTotal
) {
    public static PurchaseDetailsResponse of(
            CustomerClientResponse customerClientResponse,
            PurchaseClientResponse purchaseClientResponse,
            ProductClientResponse productClientResponse
    ) {
        double totalAmount = productClientResponse.preco() * purchaseClientResponse.quantidade();
        return new PurchaseDetailsResponse(
                customerClientResponse.nome(),
                customerClientResponse.cpf(),
                ProductResponse.of(productClientResponse),
                purchaseClientResponse.quantidade(),
                totalAmount
        );
    }
}
