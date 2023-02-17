package com.services.fraud.service;

import com.services.fraud.document.FraudCheckHistory;
import com.services.fraud.repository.FraudCheckHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public record FraudCheckHistoryService (FraudCheckHistoryRepository repository) {
    public boolean isFraudulentCustomer(Integer customerId){
        repository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .build()
        );
        return false;
    }
}
