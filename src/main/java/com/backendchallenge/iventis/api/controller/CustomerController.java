package com.backendchallenge.iventis.api.controller;

import com.backendchallenge.iventis.api.dto.CustomerTopLoyalResponse;
import com.backendchallenge.iventis.api.service.FindTopLoyalCustomersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public record CustomerController(
        FindTopLoyalCustomersService findTopLoyalCustomers
) {
    @GetMapping("/loyal")
    public List<CustomerTopLoyalResponse> topLoyalCustomers() {
        return findTopLoyalCustomers.execute();
    }
}
