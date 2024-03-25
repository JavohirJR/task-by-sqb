package com.example.taskbysqb.entity;

import lombok.Getter;

/**
 * Created on 25/03/2024.
 *
 * @author Javokhir Jaloliddinov
 */

@Getter
public enum ServiceTypes {
    WALLET("1");

    private final String id;

    ServiceTypes(String id) {
        this.id = id;
    }
}
