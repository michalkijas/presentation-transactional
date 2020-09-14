package com.example.demo.sandbox;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see https://www.codejava.net/java-core/exception/java-checked-and-unchecked-exceptions
 */
@RequiredArgsConstructor
class ExceptionTransactionService {

    private final LeadRepository repository;


    @Transactional
    public void commitWhenNoExceptionIsThrown(Long leadId, String comment) {
        Lead lead = repository.getByIdOrThrow(leadId);
        lead.setComment(comment);
    }

    @Transactional
    public void commitWhenUncheckedExceptionIsThrown(Long leadId, String comment) {
        Lead lead = repository.getByIdOrThrow(leadId);
        lead.setComment(comment);
        throw new NullPointerException();
    }

    @Transactional
    public void rollbackWhenCheckedExceptionIsThrown(Long leadId, String comment) throws Exception {
        Lead lead = repository.getByIdOrThrow(leadId);
        lead.setComment(comment);
        throw new ClassNotFoundException();
    }

    @Transactional(rollbackFor = Exception.class)
    public void commitWhenCheckedExceptionIsThrown(Long leadId, String comment) throws Exception {
        Lead lead = repository.getByIdOrThrow(leadId);
        lead.setComment(comment);
        throw new ClassNotFoundException();
    }

}
