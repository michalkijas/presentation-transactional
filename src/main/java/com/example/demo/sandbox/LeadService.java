package com.example.demo.sandbox;

import lombok.RequiredArgsConstructor;
import javax.transaction.Transactional;

@RequiredArgsConstructor
class LeadService {

    private final LeadRepository leadRepository;


    Lead createLeadUsingSaveMethod(String comment) {
        Lead lead = new Lead();
        lead.setComment(comment);
        return leadRepository.save(lead);
    }

    @Transactional
    public Lead createLeadUsingAnnotation(String comment) {
        Lead lead = new Lead();
        lead.setComment(comment);
        return lead;
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
