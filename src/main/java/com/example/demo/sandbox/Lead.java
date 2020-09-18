package com.example.demo.sandbox;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Lead {

    @GeneratedValue
    @Id
    private Long id;

    @OneToMany(mappedBy = "lead", fetch = EAGER)
    private List<LeadInteraction> interactions = new ArrayList<>();

    private String comment;

}
