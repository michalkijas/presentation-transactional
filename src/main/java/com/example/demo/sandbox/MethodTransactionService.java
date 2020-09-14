package com.example.demo.sandbox;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
class MethodTransactionService {

    private final LeadRepository leadRepository;


    Lead createLeadUsingSaveMethod(String comment) {
        Lead lead = new Lead();
        lead.setComment(comment);
        return leadRepository.save(lead);
    }

    @Transactional
    public void updateByPublicMethod(Long leadId, String comment) {
        Lead lead = leadRepository.getByIdOrThrow(leadId);
        lead.setComment(comment);
    }

    @Transactional
    void updateByPackageMethod(Long leadId, String comment) {
        Lead lead = leadRepository.getByIdOrThrow(leadId);
        lead.setComment(comment);
    }

    @Transactional
    protected void updateByProtectedMethod(Long leadId, String comment) {
        Lead lead = leadRepository.getByIdOrThrow(leadId);
        lead.setComment(comment);
    }

    void updateWithoutAnnotation(Long leadId, String comment) {
        Lead lead = leadRepository.getByIdOrThrow(leadId);
        lead.setComment(comment);
    }

}
