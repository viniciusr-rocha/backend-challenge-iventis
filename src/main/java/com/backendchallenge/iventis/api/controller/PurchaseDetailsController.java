package com.backendchallenge.iventis.api.controller;

import com.backendchallenge.iventis.api.dto.PurchaseDetailsResponse;
import com.backendchallenge.iventis.api.service.FindAllSortedPurchaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public record PurchaseDetailsController(
        FindAllSortedPurchaseService findAllSortedPurchaseService
) {
    @GetMapping
    public List<PurchaseDetailsResponse> findAllSortedPurchases() {
        return findAllSortedPurchaseService.execute();
    }
}
