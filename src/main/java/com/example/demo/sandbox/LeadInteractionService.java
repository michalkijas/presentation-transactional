package com.example.demo.sandbox;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@RequiredArgsConstructor
class LeadInteractionService {

    private final LeadInteractionRepository repository;


    @Transactional(propagation = REQUIRES_NEW)
    public LeadInteraction createLeadInteractionInNewTransaction(Lead lead, String name) {
        LeadInteraction leadInteraction = new LeadInteraction();
        leadInteraction.setLead(lead);
        leadInteraction.setName(name);
        return repository.save(leadInteraction);
    }

}
