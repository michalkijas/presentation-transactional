package com.example.demo.sandbox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LeadConfiguration {

    @Bean
    MethodTransactionService leadService(LeadRepository leadRepository) {
        return new MethodTransactionService(leadRepository);
    }

    @Bean
    PackageClassTransactionService packageClassTransactionService(LeadRepository leadRepository) {
        return new PackageClassTransactionService(leadRepository);
    }

    @Bean
    PublicClassTransactionService publicClassTransactionService(LeadRepository leadRepository) {
        return new PublicClassTransactionService(leadRepository);
    }

}
