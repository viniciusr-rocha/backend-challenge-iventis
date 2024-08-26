package com.backendchallenge.iventis.api.service;

import com.backendchallenge.iventis.api.dto.ProductResponse;
import com.backendchallenge.iventis.api.dto.PurchaseDetailsResponse;
import com.backendchallenge.iventis.api.repository.MemoryRepositoryGateway;
import com.backendchallenge.iventis.api.service.impl.FindAllSortedPurchaseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindAllSortedPurchaseServiceImplTest {

    @Mock
    private MemoryRepositoryGateway repository;

    @InjectMocks
    private FindAllSortedPurchaseServiceImpl service;

    @Test
    void testFindAllSortedPurchase() {
        var productBranco = new ProductResponse(
                12,
                "Branco",
                106.5,
                "2018",
                "2019"
        );

        var productRose = new ProductResponse(
                3,
                "Rosé",
                121.75,
                "2019",
                "2020"
        );

        var purchase1 = new PurchaseDetailsResponse(
                "Customer A",
                "any1",
                productRose,
                5,
                608.75
        );

        var purchase2 = new PurchaseDetailsResponse(
                "Customer B",
                "any1",
                productBranco,
                2,
                213.0
        );

        when(repository.findAllPurchaseDetails()).thenReturn(List.of(purchase1, purchase2));

        var result = service.execute();

        assertEquals(purchase2, result.get(0));
        assertEquals(List.of(purchase2, purchase1), result);
    }
}
