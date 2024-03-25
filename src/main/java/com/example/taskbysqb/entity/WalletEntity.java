package com.example.taskbysqb.entity;

import com.example.taskbysqb.entity.abs.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WALLET")
public class WalletEntity extends BaseEntity {

    private String ownerFullName;

    @Column(unique = true)
    private String account;

    @Column(unique = true)
    private String phone;

    private Long balance;
}
