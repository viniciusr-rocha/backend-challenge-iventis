package com.backendchallenge.iventis.api.service;

import com.backendchallenge.iventis.api.dto.PurchaseDetailsResponse;

import java.util.List;

public interface FindAllSortedPurchaseService {
    List<PurchaseDetailsResponse> execute();
}
