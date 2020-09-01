package com.example.demo.sandbox;

import org.springframework.data.jpa.repository.JpaRepository;

interface LeadRepository extends JpaRepository<Lead, Long> {

    default Lead getByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Can't find Lead with [id=%d]", id)));
    }

}
