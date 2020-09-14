package com.example.demo.sandbox

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject

/**
 * Using @SpringBootTest instead of @DataJpaTest to skip transaction
 *
 * Alternative:
 * @Import (LeadConfiguration.class)
 * @DataJpaTest
 */
@SpringBootTest
class VisibilityMethodTransactionServiceITSpec extends Specification {

    @Autowired LeadRepository repository

    @Autowired
    @Subject VisibilityMethodTransactionService sut


    def setup() {
        repository.deleteAll()
    }

    def "should use Repository.save() method to persis and propagate entity Id"() {
        when:
            Long leadId = sut.createLeadUsingSaveMethod("VALID_COMMENT").getId()
            Lead lead = repository.findById(leadId).get()
        then:
            lead.getId() == leadId
            lead.getComment() == "VALID_COMMENT"
    }

    def "should commit changes when annotation @Transactional is used on public scope method"() {
        given:
            Long leadId = sut.createLeadUsingSaveMethod("VALID_COMMENT").getId()
        when:
            sut.updateByPublicMethod(leadId, "NEW_VALID_COMMENT")
            Lead lead = repository.getByIdOrThrow(leadId)
        then:
            lead.getId() == leadId
            lead.getComment() == "NEW_VALID_COMMENT"
    }

    def "should drop changes when annotation @Transactional is used on  package-private scope method"() {
        given:
            Long leadId = sut.createLeadUsingSaveMethod("VALID_COMMENT").getId()
        when:
            sut.updateByPackageMethod(leadId, "NEW_VALID_COMMENT")
            Lead lead = repository.getByIdOrThrow(leadId)
        then:
            lead.getId() == leadId
            lead.getComment() == "VALID_COMMENT"
    }

    def "should drop changes when annotation @Transactional is used on protected scope method"() {
        given:
            Long leadId = sut.createLeadUsingSaveMethod("VALID_COMMENT").getId()
        when:
            sut.updateByProtectedMethod(leadId, "NEW_VALID_COMMENT")
            Lead lead = repository.getByIdOrThrow(leadId)
        then:
            lead.getId() == leadId
            lead.getComment() == "VALID_COMMENT"
    }

    def "should drop changes when no annotation @Transactional is used on public scope method"() {
        given:
            Long leadId = sut.createLeadUsingSaveMethod("VALID_COMMENT").getId()
        when:
            sut.updateWithoutAnnotation(leadId, "NEW_VALID_COMMENT")
            Lead lead = repository.getByIdOrThrow(leadId)
        then:
            lead.getId() == leadId
            lead.getComment() == "VALID_COMMENT"
    }

}
