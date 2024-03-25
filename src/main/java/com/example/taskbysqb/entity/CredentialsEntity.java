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
@Table(name = "CREDENTIALS")
public class CredentialsEntity extends BaseEntity {
    @Column(unique = true)
    private String username;

    private String password;
}
