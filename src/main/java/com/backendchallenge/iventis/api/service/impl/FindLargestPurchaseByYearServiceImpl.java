package com.backendchallenge.iventis.api.service.impl;

import com.backendchallenge.iventis.api.dto.PurchaseDetailsResponse;
import com.backendchallenge.iventis.api.repository.MemoryRepositoryGateway;
import com.backendchallenge.iventis.api.service.FindLargestPurchaseByYearService;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public record FindLargestPurchaseByYearServiceImpl(
        MemoryRepositoryGateway memoryRepositoryGateway
) implements FindLargestPurchaseByYearService {

    @Override
    public PurchaseDetailsResponse execute(Integer year) {
        return memoryRepositoryGateway.findAllPurchaseDetailsByYear(year).stream()
                .max(Comparator.comparingDouble(PurchaseDetailsResponse::valorTotal))
                .orElse(null);
    }
}
