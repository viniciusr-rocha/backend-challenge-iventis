package com.backendchallenge.iventis.api.controller;

import com.backendchallenge.iventis.api.dto.PurchaseDetailsResponse;
import com.backendchallenge.iventis.api.service.FindAllSortedPurchaseService;
import com.backendchallenge.iventis.api.service.FindLargestPurchaseByYearService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public record PurchaseDetailsController(
        FindAllSortedPurchaseService findAllSortedPurchaseService,
        FindLargestPurchaseByYearService findLargestPurchaseByYearService
) {
    @GetMapping
    public List<PurchaseDetailsResponse> findAllSortedPurchases() {
        return findAllSortedPurchaseService.execute();
    }

    @GetMapping("/largest-purchase/{year}")
    public PurchaseDetailsResponse findLargestPurchaseByYear(@PathVariable Integer year) {
        return findLargestPurchaseByYearService.execute(year);
    }
}
