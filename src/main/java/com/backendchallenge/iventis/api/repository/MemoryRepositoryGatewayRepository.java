package com.backendchallenge.iventis.api.repository;

import com.backendchallenge.iventis.api.client.MockDataClient;
import com.backendchallenge.iventis.api.client.response.CustomerClientResponse;
import com.backendchallenge.iventis.api.client.response.ProductClientResponse;
import com.backendchallenge.iventis.api.client.response.PurchaseClientResponse;
import com.backendchallenge.iventis.api.dto.PurchaseDetailsResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryRepositoryGatewayRepository implements MemoryRepositoryGateway {

    private final MockDataClient mockDataClient;

    public MemoryRepositoryGatewayRepository(MockDataClient mockDataClient) {
        this.mockDataClient = mockDataClient;
    }

    private final Map<String, CustomerClientResponse> customersTable = new HashMap<>();
    private final Map<Integer, ProductClientResponse> productsTable = new HashMap<>();
    private List<PurchaseDetailsResponse> purchases = new ArrayList<>();

    @PostConstruct
    private void initializeData() {
        var customers = mockDataClient.listAllCustomers();
        var products = mockDataClient.listAllProducts();

        customers.forEach(customer -> customersTable.put(customer.cpf(), customer));
        products.forEach(product -> productsTable.put(product.codigo(), product));

        this.purchases = customers.stream()
                .flatMap(customerClientResponse -> customerClientResponse.compras().stream()
                        .map(purchaseClientResponse -> createPurchaseResponse(customerClientResponse, purchaseClientResponse))
                ).toList();
    }

    @Override
    public List<CustomerClientResponse> findAllCustomers() {
        return new ArrayList<>(customersTable.values());
    }

    @Override
    public Optional<CustomerClientResponse> findCustomerByCpf(String cpf) {
        return Optional.ofNullable(customersTable.get(cpf));
    }

    @Override
    public List<PurchaseDetailsResponse> findAllPurchaseDetails() {
        return purchases;
    }

    @Override
    public List<PurchaseDetailsResponse> findAllPurchaseDetailsByYear(Integer year) {
        return purchases.stream()
                .filter(purchaseDetailsResponse -> purchaseDetailsResponse.produto().anoCompra().equals(String.valueOf(year)))
                .toList();
    }

    private PurchaseDetailsResponse createPurchaseResponse(
            CustomerClientResponse customerClientResponse,
            PurchaseClientResponse purchaseClientResponse
    ) {
        var productCode = Integer.parseInt(purchaseClientResponse.codigo());
        var productResponse = productsTable.get(productCode);
        return PurchaseDetailsResponse.of(customerClientResponse, purchaseClientResponse, productResponse);
    }
}
