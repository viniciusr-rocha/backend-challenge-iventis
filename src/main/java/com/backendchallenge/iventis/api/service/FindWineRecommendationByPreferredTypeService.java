package com.backendchallenge.iventis.api.service;

import com.backendchallenge.iventis.api.dto.ProductRecommendationResponse;

public interface FindWineRecommendationByPreferredTypeService {
    ProductRecommendationResponse execute(String cpf);
}
