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
class VisibilityClassPackageTransactionServiceSpec extends Specification {

    @Autowired LeadRepository repository

    @Autowired
    @Subject VisibilityClassPackageTransactionService sut


    def setup() {
        repository.deleteAll()
    }

    def "should commit changes when using annotation on package-private class level and method with public scope"() {
        given:
            Long leadId = sut.createLeadUsingSaveMethod("VALID_COMMENT").getId()
        when:
            sut.updateByPublicMethod(leadId, "NEW_VALID_COMMENT")
            Lead lead = repository.getByIdOrThrow(leadId)
        then:
            lead.getId() == leadId
            lead.getComment() == "NEW_VALID_COMMENT"
    }

    def "should drop changes when using annotation on package-private class level and method with package scope"() {
        given:
            Long leadId = sut.createLeadUsingSaveMethod("VALID_COMMENT").getId()
        when:
            sut.updateByPackageMethod(leadId, "NEW_VALID_COMMENT")
            Lead lead = repository.getByIdOrThrow(leadId)
        then:
            lead.getId() == leadId
            lead.getComment() == "VALID_COMMENT"
    }

    def "should drop changes when using annotation on package-private class level and method with protected scope"() {
        given:
            Long leadId = sut.createLeadUsingSaveMethod("VALID_COMMENT").getId()
        when:
            sut.updateByProtectedMethod(leadId, "NEW_VALID_COMMENT")
            Lead lead = repository.getByIdOrThrow(leadId)
        then:
            lead.getId() == leadId
            lead.getComment() == "VALID_COMMENT"
    }

}
