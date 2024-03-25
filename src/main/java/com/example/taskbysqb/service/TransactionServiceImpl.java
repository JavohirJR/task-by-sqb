package com.example.taskbysqb.service;

import com.example.taskbysqb.entity.ServiceTypes;
import com.example.taskbysqb.entity.TransactionEntity;
import com.example.taskbysqb.repository.TransactionEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsdl.PerformTransactionArguments;

/**
 * Created on 25/03/2024.
 *
 * @author Javokhir Jaloliddinov
 */
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl {
    private TransactionEntityRepository transactionEntityRepository;

    @Transactional
    public Long createSuccessTransaction(PerformTransactionArguments request) {
        String provider = request.getUsername();
        String extId = String.valueOf(request.getTransactionId());
        transactionEntityRepository.findTransactionEntityByProviderAndExtId(provider, extId)
                .orElseThrow(() -> new RuntimeException("Transaction is already exist"));
        TransactionEntity entity = new TransactionEntity(request.getAmount(), ServiceTypes.WALLET, request.toString(), provider, extId, TransactionEntity.TransactionStatus.SUCCESS);
        transactionEntityRepository.save(entity);
        return entity.getId();
    }
}
