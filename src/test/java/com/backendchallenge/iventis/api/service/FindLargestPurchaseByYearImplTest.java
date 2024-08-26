package com.backendchallenge.iventis.api.service;

import com.backendchallenge.iventis.api.dto.ProductResponse;
import com.backendchallenge.iventis.api.dto.PurchaseDetailsResponse;
import com.backendchallenge.iventis.api.repository.MemoryRepositoryGateway;
import com.backendchallenge.iventis.api.service.impl.FindLargestPurchaseByYearServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindLargestPurchaseByYearImplTest {

    @Mock
    private MemoryRepositoryGateway repository;

    @InjectMocks
    private FindLargestPurchaseByYearServiceImpl service;

    @Test
    void findLargestPurchaseByYear() {
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

        when(repository.findAllPurchaseDetailsByYear(2020)).thenReturn(List.of(purchase1, purchase2));

        var result = service.execute(2020);

        assertEquals(purchase1, result);
    }
}
