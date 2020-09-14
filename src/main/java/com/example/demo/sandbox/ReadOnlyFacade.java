package com.example.demo.sandbox;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
public class ReadOnlyFacade {

    private final LeadRepository repository;


    //    @Transactional
    public Lead create(String comment) {
        Lead lead = new Lead();
        lead.setComment(comment);
        return repository.save(lead);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<Lead> findAll() {
        return repository.findAll();
    }

}
