package com.example.demo.sandbox;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
class LeadInteraction {

    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne
    private Lead lead;

    private String name;

}
