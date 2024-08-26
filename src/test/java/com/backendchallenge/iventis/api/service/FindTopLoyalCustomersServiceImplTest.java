package com.backendchallenge.iventis.api.service;

import com.backendchallenge.iventis.api.client.response.CustomerClientResponse;
import com.backendchallenge.iventis.api.client.response.PurchaseClientResponse;
import com.backendchallenge.iventis.api.dto.CustomerTopLoyalResponse;
import com.backendchallenge.iventis.api.dto.ProductResponse;
import com.backendchallenge.iventis.api.dto.PurchaseDetailsResponse;
import com.backendchallenge.iventis.api.repository.MemoryRepositoryGateway;
import com.backendchallenge.iventis.api.service.impl.FindTopLoyalCustomersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindTopLoyalCustomersServiceImplTest {

    @Mock
    private MemoryRepositoryGateway repository;

    @InjectMocks
    private FindTopLoyalCustomersServiceImpl service;

    @Test
    void testFindTopLoyalCustomers() {
        var top1 = new CustomerTopLoyalResponse(
                "Top 1",
                "any",
                5,
                250.5
        );
        var top2 = new CustomerTopLoyalResponse(
                "Top 2",
                "any",
                2,
                134.2
        );
        var top3 = new CustomerTopLoyalResponse(
                "Top 3",
                "any",
                10,
                115.0
        );

        var customer1 = new CustomerClientResponse(
                top1.nome(),
                top1.cpf(),
                List.of(
                        new PurchaseClientResponse("1", 2)
                )
        );

        var customer2 = new CustomerClientResponse(
                top2.nome(),
                top2.cpf(),
                List.of()
        );

        var customer3 = new CustomerClientResponse(
                top3.nome(),
                top3.cpf(),
                List.of()
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

        var customerList = List.of(customer1, customer2, customer3);

        when(repository.findAllCustomers()).thenReturn(customerList);
        when(repository.findAllPurchaseDetails()).thenReturn(List.of(purchase1));

        var result = service.execute();

        assertEquals(customer1.nome(), result.get(0).nome());
        assertEquals(customer2.nome(), result.get(1).nome());
        assertEquals(customer3.nome(), result.get(2).nome());
    }
}
