package com.example.taskbysqb;

import com.example.taskbysqb.entity.CredentialsEntity;
import com.example.taskbysqb.repository.CredentialEntityRepository;
import com.example.taskbysqb.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskBySqbApplication implements CommandLineRunner {

    @Autowired
    private CredentialEntityRepository credentialEntityRepository;

    public static void main(String[] args) {
        SpringApplication.run(TaskBySqbApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        String username = "username";
        String password = "password";
        if (!credentialEntityRepository.findByUsername(username).isPresent()) {
            credentialEntityRepository.save(new CredentialsEntity(username, PasswordEncoder.encode(username, password)));
        }
    }
}
