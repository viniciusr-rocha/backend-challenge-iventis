package com.backendchallenge.iventis.api.service;

import com.backendchallenge.iventis.api.dto.CustomerTopLoyalResponse;

import java.util.List;

public interface FindTopLoyalCustomersService {
    List<CustomerTopLoyalResponse> execute();
}
