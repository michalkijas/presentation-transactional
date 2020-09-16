package com.example.demo.sandbox;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@RequiredArgsConstructor
class VisibilityMethodTransactionService {

    private final LeadRepository leadRepository;

//    private final EntityManager em;
//
//    void run() {
//        EntityTransaction transaction = em.getTransaction();
//        try {
//            transaction.begin();
////        code
////        code
////        code
//            transaction.commit();
//        } catch (Exception e) {
//            transaction.rollback();
//        }
//    }


    @Transactional
    public Lead createLeadUsingSaveMethod2(String comment) {
        Lead lead = new Lead();
        lead.setComment(comment);
        return lead;
    }

    Lead createLeadUsingSaveMethod(String comment) {
        Lead lead = new Lead();
        lead.setComment(comment);
        return leadRepository.save(lead);
    }

    @Transactional
    public void updateByPublicMethod(Long leadId, String comment) {
        Lead lead = leadRepository.getByIdOrThrow(leadId);
        lead.setComment(comment);
//        updateByPublicMethod1(...);
    }

//    @Transactional
//    public void updateByPublicMethod1(Long leadId, String comment) {
//        Lead lead = leadRepository.getByIdOrThrow(leadId);
//        lead.setComment(comment);
//    }

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
