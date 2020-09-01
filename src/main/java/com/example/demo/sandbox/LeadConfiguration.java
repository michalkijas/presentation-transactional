package com.example.demo.sandbox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LeadConfiguration {

    @Bean
    LeadService leadService(LeadRepository leadRepository) {
        return new LeadService(leadRepository);
    }

}
