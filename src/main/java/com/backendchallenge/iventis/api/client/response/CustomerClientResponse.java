package com.backendchallenge.iventis.api.client.response;

import java.util.List;

public record CustomerClientResponse(
        String nome,
        String cpf,
        List<PurchaseClientResponse> compras
) {
}
