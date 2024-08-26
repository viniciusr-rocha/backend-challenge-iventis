package com.backendchallenge.iventis.api.service.impl;

import com.backendchallenge.iventis.api.client.response.CustomerClientResponse;
import com.backendchallenge.iventis.api.dto.CustomerTopLoyalResponse;
import com.backendchallenge.iventis.api.dto.PurchaseDetailsResponse;
import com.backendchallenge.iventis.api.repository.MemoryRepositoryGateway;
import com.backendchallenge.iventis.api.service.FindTopLoyalCustomersService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
public record FindTopLoyalCustomersServiceImpl(
        MemoryRepositoryGateway memoryRepositoryGateway
) implements FindTopLoyalCustomersService {

    @Override
    public List<CustomerTopLoyalResponse> execute() {
        return memoryRepositoryGateway.findAllCustomers().stream()
                .map(this::toCustomerTopLoyalResponse)
                .sorted(Comparator.comparingDouble(CustomerTopLoyalResponse::valorTotalCompras).reversed())
                .limit(3)
                .toList();
    }

    private CustomerTopLoyalResponse toCustomerTopLoyalResponse(CustomerClientResponse customerClientResponse) {
        var totalAmount = findPurchasesByCpf(customerClientResponse)
                .mapToDouble(PurchaseDetailsResponse::valorTotal)
                .sum();
        return CustomerTopLoyalResponse.of(customerClientResponse, totalAmount);
    }

    private Stream<PurchaseDetailsResponse> findPurchasesByCpf(CustomerClientResponse customerClientResponse) {
        return memoryRepositoryGateway.findAllPurchaseDetails().stream()
                .filter(purchase -> purchase.cpf().equals(customerClientResponse.cpf()));
    }
}
