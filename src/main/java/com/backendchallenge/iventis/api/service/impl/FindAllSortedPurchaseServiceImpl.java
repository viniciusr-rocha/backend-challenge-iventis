package com.backendchallenge.iventis.api.service.impl;

import com.backendchallenge.iventis.api.dto.PurchaseDetailsResponse;
import com.backendchallenge.iventis.api.repository.MemoryRepositoryGateway;
import com.backendchallenge.iventis.api.service.FindAllSortedPurchaseService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public record FindAllSortedPurchaseServiceImpl(
        MemoryRepositoryGateway memoryRepositoryGateway
) implements FindAllSortedPurchaseService {

    @Override
    public List<PurchaseDetailsResponse> execute() {
        return memoryRepositoryGateway.findAllPurchaseDetails().stream()
                .sorted(Comparator.comparingDouble(PurchaseDetailsResponse::valorTotal))
                .toList();
    }
}
