package com.example.taskbysqb.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 25/03/2024.
 *
 * @author Javokhir Jaloliddinov
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckCredential {
}
