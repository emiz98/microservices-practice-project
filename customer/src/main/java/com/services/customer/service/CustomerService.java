package com.services.customer.service;

import com.services.clients.fraud.FraudClient;
import com.services.clients.fraud.dto.FraudCheckResponse;
import com.services.clients.notification.NotificationClient;
import com.services.clients.notification.dto.NotificationRequest;
import com.services.customer.dto.CustomerRegistrationRequest;
import com.services.customer.entity.Customer;
import com.services.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        RestTemplate restTemplate,
        NotificationClient notificationClient,
        FraudClient fraudClient) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // todo: check if email valid
        // todo: check if email not taken
        customerRepository.saveAndFlush(customer);

//        Using rest template
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD/api/v1/fraud/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId()
//        );


        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());  // Using openfeign

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        // todo: make it async. i.e add to queue
        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, whats up...",
                                customer.getFirstName())
                )
        );
    }

}
