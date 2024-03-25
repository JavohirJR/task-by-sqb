package com.example.taskbysqb.repository;

import com.example.taskbysqb.entity.CredentialsEntity;
import com.example.taskbysqb.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created on 25/03/2024.
 *
 * @author Javokhir Jaloliddinov
 */
public interface CredentialEntityRepository extends JpaRepository<CredentialsEntity, Long> {
    Optional<CredentialsEntity> findByUsername(String username);

}
