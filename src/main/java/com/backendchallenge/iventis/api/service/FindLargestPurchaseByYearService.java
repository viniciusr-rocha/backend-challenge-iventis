package com.backendchallenge.iventis.api.service;

import com.backendchallenge.iventis.api.dto.PurchaseDetailsResponse;

public interface FindLargestPurchaseByYearService {
    PurchaseDetailsResponse execute(Integer year);
}
