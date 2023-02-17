package com.services.fraud.repository;

import com.services.fraud.document.FraudCheckHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudCheckHistoryRepository extends MongoRepository<FraudCheckHistory,Integer> {
}
