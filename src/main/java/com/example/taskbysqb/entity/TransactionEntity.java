package com.example.taskbysqb.entity;

import com.example.taskbysqb.entity.abs.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created on 25/03/2024.
 *
 * @author Javokhir Jaloliddinov
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRANSACTIONS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"provider", "extId"})})
public class TransactionEntity extends BaseEntity {

    private Long amount;

    @Enumerated(value = EnumType.STRING)
    private ServiceTypes service;

    private String details;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(nullable = false)
    private String extId;

    @Column(nullable = false)
    private TransactionStatus status;

    public enum TransactionStatus{
        SUCCESS,
        FAILED

    }

}
