package com.example.demo.readonly;


import com.example.demo.sandbox.Lead;
import com.example.demo.sandbox.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
public class ReadOnlyService {

    private final LeadRepository repository;


    @Transactional
    public Lead create(String comment) {
        Lead lead = new Lead();
        lead.setComment(comment);
        return repository.save(lead);
    }

    @Transactional(readOnly = true)
    public List<Lead> findAll() {
        return repository.findAll();
    }

}
