package com.example.demo.sandbox;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Lead {

    @GeneratedValue
    @Id
    private Long id;

    @OneToMany(mappedBy = "lead")
    private List<LeadInteraction> interactions = new ArrayList<>();

    private String comment;

}
