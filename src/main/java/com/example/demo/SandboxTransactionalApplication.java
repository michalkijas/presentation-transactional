package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = SandboxTransactionalApplication.class)
public class SandboxTransactionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SandboxTransactionalApplication.class, args);
    }

}
