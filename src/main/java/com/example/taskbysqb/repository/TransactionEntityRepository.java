package com.example.taskbysqb.repository;

import com.example.taskbysqb.entity.TransactionEntity;
import com.example.taskbysqb.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created on 25/03/2024.
 *
 * @author Javokhir Jaloliddinov
 */
public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, Long> {
    Optional<TransactionEntity> findTransactionEntityByProviderAndExtId(String provider, String extId);
}
