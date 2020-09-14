package com.example.demo.sandbox;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
class TransactionPropagationService {

    private final LeadRepository leadRepository;
    private final LeadInteractionService leadInteractionService;


    @Transactional
    public Lead runInTransaction(String comment, String leadInteractionName) {
        Lead lead = new Lead();
        lead.setComment(comment);
        Lead newLead = leadRepository.save(lead);
        leadInteractionService.createLeadInteractionInNewTransaction(newLead, leadInteractionName);
        return newLead;
    }

    /**
     * Repository.save()
     */
    public Lead runWithoutTransaction(String comment, String leadInteractionName) {
        Lead lead = new Lead();
        lead.setComment(comment);
        Lead newLead = leadRepository.save(lead);
        leadInteractionService.createLeadInteractionInNewTransaction(newLead, leadInteractionName);
        return newLead;
    }

}
