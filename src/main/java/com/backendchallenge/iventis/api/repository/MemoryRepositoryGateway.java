package com.backendchallenge.iventis.api.repository;

import com.backendchallenge.iventis.api.client.response.CustomerClientResponse;
import com.backendchallenge.iventis.api.dto.PurchaseDetailsResponse;

import java.util.List;
import java.util.Optional;

public interface MemoryRepositoryGateway {
    List<CustomerClientResponse> findAllCustomers();

    Optional<CustomerClientResponse> findCustomerByCpf(String cpf);

    List<PurchaseDetailsResponse> findAllPurchaseDetails();

    List<PurchaseDetailsResponse> findAllPurchaseDetailsByYear(Integer year);
}
