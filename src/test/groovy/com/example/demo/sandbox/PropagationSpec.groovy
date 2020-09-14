package com.example.demo.sandbox


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
import spock.lang.Specification
import spock.lang.Subject

import javax.persistence.EntityManager

@SpringBootTest
class PropagationSpec extends Specification {

    @Autowired LeadRepository leadRepository
    @Autowired LeadInteractionRepository leadInteractionRepository
    @Autowired EntityManager entityManager

    @Autowired
    @Subject TransactionPropagationService sut

    def setup() {
        leadRepository.deleteAll()
    }

    def cleanup() {
        leadInteractionRepository.deleteAll()
    }


    def "should throw DataIntegrityViolationException when trying to create lead interaction referring to lead created in external transaction"() {
        when:
            sut.runInTransaction("VALID_LEAD_COMMENT", "VALID_LEAD_INTERACTION_NAME")
        then:
            thrown(DataIntegrityViolationException)
    }


    def "should persist lead and lead interaction when lead is created using Repository.save() method without transaction"() {
        when:
            Lead result = sut.runWithoutTransaction("VALID_LEAD_COMMENT", "VALID_LEAD_INTERACTION_NAME")
        then:
            noExceptionThrown()
        and:
            Lead lead = leadRepository.getByIdOrThrow(result.getId())
            lead.getComment() == "VALID_LEAD_COMMENT"
            lead.getInteractions().get(0).getName() == "VALID_LEAD_INTERACTION_NAME"
    }

}
