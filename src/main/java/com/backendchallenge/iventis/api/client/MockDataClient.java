package com.backendchallenge.iventis.api.client;

import com.backendchallenge.iventis.api.client.response.CustomerClientResponse;
import com.backendchallenge.iventis.api.client.response.ProductClientResponse;

import java.util.List;

public interface MockDataClient {
    List<ProductClientResponse> listAllProducts();

    List<CustomerClientResponse> listAllCustomers();
}
