package com.backendchallenge.iventis.api.service;

import com.backendchallenge.iventis.api.client.response.CustomerClientResponse;
import com.backendchallenge.iventis.api.client.response.PurchaseClientResponse;
import com.backendchallenge.iventis.api.dto.ProductResponse;
import com.backendchallenge.iventis.api.dto.PurchaseDetailsResponse;
import com.backendchallenge.iventis.api.repository.MemoryRepositoryGateway;
import com.backendchallenge.iventis.api.service.impl.FindWineRecommendationByPreferredTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindWineRecommendationByPreferredTypeServiceImplTest {

    @Mock
    private MemoryRepositoryGateway repository;

    @InjectMocks
    private FindWineRecommendationByPreferredTypeServiceImpl service;

    @Test
    void testFindWineRecommendationByPreferredTypeImpl() {
        var productRose = new ProductResponse(
                3,
                "Rosé",
                121.75,
                "2019",
                "2020"
        );

        var productBranco = new ProductResponse(
                12,
                "Branco",
                106.5,
                "2018",
                "2019"
        );

        var purchase1 = new PurchaseDetailsResponse(
                "Customer A",
                "any1",
                productRose,
                5,
                608.75
        );

        var purchase2 = new PurchaseDetailsResponse(
                "Customer A",
                "any1",
                productBranco,
                10,
                832.5
        );

        var customer1 = new CustomerClientResponse(
                "Customer A",
                "any1",
                List.of(
                        new PurchaseClientResponse("1", 2)
                )
        );

        when(repository.findCustomerByCpf("any1")).thenReturn(Optional.of(customer1));
        when(repository.findAllPurchaseDetails()).thenReturn(List.of(purchase1, purchase2));

        var result = service.execute("any1");

        assertEquals("Branco", result.tipoVinho());
    }
}
