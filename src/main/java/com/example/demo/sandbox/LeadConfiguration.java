package com.example.demo.sandbox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LeadConfiguration {

    @Bean
    LeadInteractionService leadInteractionService(LeadInteractionRepository leadInteractionRepository) {
        return new LeadInteractionService(leadInteractionRepository);
    }

    @Bean
    ExceptionTransactionService exceptionTransactionService(LeadRepository leadRepository) {
        return new ExceptionTransactionService(leadRepository);
    }

    @Bean
    TransactionPropagationService transactionPropagationService(LeadRepository leadRepository, LeadInteractionService leadInteractionService) {
        return new TransactionPropagationService(leadRepository, leadInteractionService);
    }

    @Bean
    VisibilityClassPackageTransactionService visibilityClassPackageTransactionService(LeadRepository leadRepository) {
        return new VisibilityClassPackageTransactionService(leadRepository);
    }

    @Bean
    VisibilityClassPublicTransactionService visibilityClassPublicTransactionService(LeadRepository leadRepository) {
        return new VisibilityClassPublicTransactionService(leadRepository);
    }

    @Bean
    VisibilityMethodTransactionService visibilityMethodTransactionService(LeadRepository leadRepository) {
        return new VisibilityMethodTransactionService(leadRepository);
    }

}
