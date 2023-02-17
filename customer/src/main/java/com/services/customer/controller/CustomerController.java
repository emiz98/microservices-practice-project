package com.services.customer.controller;

import com.services.customer.dto.CustomerRegistrationRequest;
import com.services.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController (CustomerService customerService) {

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
        log.info("New customer registered {}",customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }
}