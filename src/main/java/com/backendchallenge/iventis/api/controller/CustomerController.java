package com.backendchallenge.iventis.api.controller;

import com.backendchallenge.iventis.api.dto.CustomerTopLoyalResponse;
import com.backendchallenge.iventis.api.dto.ProductRecommendationResponse;
import com.backendchallenge.iventis.api.service.FindTopLoyalCustomersService;
import com.backendchallenge.iventis.api.service.FindWineRecommendationByPreferredTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public record CustomerController(
        FindTopLoyalCustomersService findTopLoyalCustomersService,
        FindWineRecommendationByPreferredTypeService findWineRecommendationByPreferredTypeService
) {
    @GetMapping("/loyal")
    public List<CustomerTopLoyalResponse> topLoyalCustomers() {
        return findTopLoyalCustomersService.execute();
    }

    @GetMapping("/recommendation/{cpf}/type")
    public ProductRecommendationResponse wineRecommendation(@PathVariable String cpf) {
        return findWineRecommendationByPreferredTypeService.execute(cpf);
    }
}
