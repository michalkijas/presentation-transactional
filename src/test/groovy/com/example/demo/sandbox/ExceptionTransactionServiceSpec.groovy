package com.example.demo.sandbox

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
class ExceptionTransactionServiceSpec extends Specification {

    @Autowired LeadRepository repository

    @Autowired
    @Subject ExceptionTransactionService sut


    def setup() {
        repository.deleteAll()
    }

    def "should commit changes when no exception is thrown"() {
        given: "create new lead"
            Lead lead = new Lead()
            lead.setComment("VALID_COMMENT")
            Lead newLead = repository.save(lead)
        when: "updating lead"
            sut.commitWhenNoExceptionIsThrown(newLead.getId(), "NEW_VALID_COMMENT")
        then: "changes are committed"
            Lead updatedLead = repository.getByIdOrThrow(lead.getId())
            updatedLead.getComment() == "NEW_VALID_COMMENT"
    }


    def "should rollback changes when unchecked exception is thrown"() {
        given: "create new lead"
            Lead lead = new Lead()
            lead.setComment("VALID_COMMENT")
            Lead newLead = repository.save(lead)
        when: "trying to update lead"
            sut.commitWhenUncheckedExceptionIsThrown(newLead.getId(), "NEW_VALID_COMMENT")
        then: "unchecked exception is thrown"
            thrown(NullPointerException)
        and: "rollback changes"
            Lead updatedLead = repository.getByIdOrThrow(lead.getId())
            updatedLead.getComment() == "VALID_COMMENT"
    }


    def "should commit changes when checked exception is thrown"() {
        given: "create new lead"
            Lead lead = new Lead()
            lead.setComment("VALID_COMMENT")
            Lead newLead = repository.save(lead)
        when: "trying to update lead"
            sut.rollbackWhenCheckedExceptionIsThrown(newLead.getId(), "NEW_VALID_COMMENT")
        then: "checked exception is thrown"
            thrown(ClassNotFoundException)
        and: "commit changes"
            Lead updatedLead = repository.getByIdOrThrow(lead.getId())
            updatedLead.getComment() == "NEW_VALID_COMMENT"
    }


    def "should rollback changes when checked exception are configured to rollback"() {
        given: "create new lead"
            Lead lead = new Lead()
            lead.setComment("VALID_COMMENT")
            Lead newLead = repository.save(lead)
        when: "trying to update lead in transaction configured to rollback on checked exception"
            sut.commitWhenCheckedExceptionIsThrown(newLead.getId(), "NEW_VALID_COMMENT")
        then: "checked exception is thrown"
            thrown(ClassNotFoundException)
        and: "rollback changes"
            Lead updatedLead = repository.getByIdOrThrow(lead.getId())
            updatedLead.getComment() == "VALID_COMMENT"
    }

}
