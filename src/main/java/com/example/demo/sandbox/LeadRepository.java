package com.example.demo.sandbox;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

interface LeadRepository extends JpaRepository<Lead, Long> {

    Lead findByComment(String comment);

    Page<Lead> findAll(Specification<Lead> specification, Pageable page);

    default Lead getByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Can't find Lead with [id=%d]", id)));
    }

}
