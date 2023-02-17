package com.services.fraud.controller;

import com.services.fraud.dto.FraudCheckResponse;
import com.services.fraud.service.FraudCheckHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud")
public record FraudCheckHistoryController(FraudCheckHistoryService service) {
    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId){
        boolean isFraud = service.isFraudulentCustomer(customerId);
        log.info("Fraud check request for customer {}",customerId);
        return new FraudCheckResponse(isFraud);
    }
}
