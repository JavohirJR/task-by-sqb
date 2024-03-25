package com.example.taskbysqb.service;

import com.example.taskbysqb.security.CheckCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wsdl.PerformTransactionArguments;
import wsdl.PerformTransactionResult;

import java.util.Map;
import java.util.Objects;

/**
 * Created on 25/03/2024.
 *
 * @author Javokhir Jaloliddinov
 */

@Service
@RequiredArgsConstructor
public class PaymentServiceFactory {
    private final Map<String, PaymentService> paymentServiceMap;

    public PaymentService getService(String serviceId) {
        PaymentService paymentService = paymentServiceMap.get(serviceId);
        if (Objects.isNull(paymentService))
            throw new RuntimeException("Service is not found");
        return paymentService;
    }

}
