package com.example.taskbysqb.repository;

import com.example.taskbysqb.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created on 25/03/2024.
 *
 * @author Javokhir Jaloliddinov
 */
public interface WalletEntityRepository extends JpaRepository<WalletEntity, Long> {
    Optional<WalletEntity> findByAccountOrPhone(String account, String phone);
    Optional<WalletEntity> findByAccount(String account);
    Optional<WalletEntity> findByPhone(String phone);
}
