package com.example.demo.sandbox;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
class TransactionPropagationService {

    private final LeadRepository leadRepository;
    private final LeadInteractionService leadInteractionService;


//    @Transactional
//    public void runFirst(String comment, String leadInteractionName) {
////        runInTransaction1(comment);
//    }
//
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Transactional(propagation = Propagation.MANDATORY)
//    @Transactional(propagation = Propagation.SUPPORTS)
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    @Transactional(propagation = Propagation.NEVER)
//    @Transactional(propagation = Propagation.NESTED)
//    public void runInTransaction1(String comment, String leadInteractionName) {
//        Lead lead = new Lead();
//        lead.setComment(comment);
//    }


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
