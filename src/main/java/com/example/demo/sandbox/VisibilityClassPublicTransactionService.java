package com.example.demo.sandbox;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class VisibilityClassPublicTransactionService {

    private final LeadRepository leadRepository;


    Lead createLeadUsingSaveMethod(String comment) {
        Lead lead = new Lead();
        lead.setComment(comment);
        return leadRepository.save(lead);
    }

    public void updateByPublicMethod(Long leadId, String comment) {
        Lead lead = leadRepository.getByIdOrThrow(leadId);
        lead.setComment(comment);
    }

    void updateByPackageMethod(Long leadId, String comment) {
        Lead lead = leadRepository.getByIdOrThrow(leadId);
        lead.setComment(comment);
    }

    protected void updateByProtectedMethod(Long leadId, String comment) {
        Lead lead = leadRepository.getByIdOrThrow(leadId);
        lead.setComment(comment);
    }

}
