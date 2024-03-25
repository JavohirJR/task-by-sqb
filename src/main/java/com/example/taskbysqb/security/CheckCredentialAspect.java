package com.example.taskbysqb.security;

import com.example.taskbysqb.entity.CredentialsEntity;
import com.example.taskbysqb.repository.CredentialEntityRepository;
import com.example.taskbysqb.utils.PasswordEncoder;
import jakarta.xml.bind.JAXBElement;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import wsdl.GenericArguments;
import wsdl.GenericParam;

/**
 * Created on 25/03/2024.
 *
 * @author Javokhir Jaloliddinov
 */

@Aspect
@Component
@RequiredArgsConstructor
public class CheckCredentialAspect {
    private final CredentialEntityRepository repository;

    @Before("@annotation(com.example.taskbysqb.security.CheckCredential)")
    public void checkCredential(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (isActualObject(args)) {
            JAXBElement<GenericArguments> arguments = (JAXBElement<GenericArguments>) args[0];
            GenericArguments req = arguments.getValue();
            CredentialsEntity user = repository.findByUsername(req.getUsername())
                    .orElseThrow(() -> new RuntimeException("User is not exist"));
            PasswordEncoder.compareCredentials(user.getPassword(), req.getUsername(), req.getPassword());
        } else {
            throw new RuntimeException("Unauthorized");
        }
    }

    private static boolean isActualObject(Object[] args) {
        return args.length == 1 && args[0] instanceof JAXBElement<?>;
    }
}
