package com.backendchallenge.iventis.api.service.impl;

import com.backendchallenge.iventis.api.client.response.CustomerClientResponse;
import com.backendchallenge.iventis.api.dto.ProductRecommendationResponse;
import com.backendchallenge.iventis.api.dto.PurchaseDetailsResponse;
import com.backendchallenge.iventis.api.repository.MemoryRepositoryGateway;
import com.backendchallenge.iventis.api.service.FindWineRecommendationByPreferredTypeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public record FindWineRecommendationByPreferredTypeServiceImpl(
        MemoryRepositoryGateway memoryRepositoryGateway
) implements FindWineRecommendationByPreferredTypeService {

    @Override
    public ProductRecommendationResponse execute(String cpf) {
        return memoryRepositoryGateway.findCustomerByCpf(cpf)
                .map(this::recommendWine)
                .orElse(null);
    }

    private ProductRecommendationResponse recommendWine(CustomerClientResponse customer) {
        var allPurchases = memoryRepositoryGateway.findAllPurchaseDetails();
        var typeWineMap = mapCustomerWinePreferences(customer, allPurchases);
        var mostPurchasedWineType = identifyMostPurchasedWineType(typeWineMap);
        return recommendProduct(mostPurchasedWineType, allPurchases);
    }

    private Map<String, Integer> mapCustomerWinePreferences(
            CustomerClientResponse customer,
            List<PurchaseDetailsResponse> purchases
    ) {
        var wineTypeCounts = new HashMap<String, Integer>();
        purchases.stream()
                .filter(purchaseResponse -> purchaseResponse.cpf().equals(customer.cpf()))
                .forEach(purchaseResponse ->
                        wineTypeCounts.merge(
                                purchaseResponse.produto().tipoVinho(),
                                purchaseResponse.quantidade(),
                                Integer::sum
                        )
                );
        return wineTypeCounts;
    }

    private String identifyMostPurchasedWineType(Map<String, Integer> typeWineMap) {
        return typeWineMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private ProductRecommendationResponse recommendProduct(
            String wineType,
            List<PurchaseDetailsResponse> purchases
    ) {
        return purchases.stream()
                .filter(purchaseDetailsResponse -> purchaseDetailsResponse.produto().tipoVinho().equals(wineType))
                .findFirst()
                .map(ProductRecommendationResponse::of)
                .orElse(null);
    }
}
