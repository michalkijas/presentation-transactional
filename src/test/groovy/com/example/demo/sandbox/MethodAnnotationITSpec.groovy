package com.example.demo.sandbox

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Subject

//@Import(LeadConfiguration.class)
//@DataJpaTest
/**
 * Using @SpringBootTest instead of @DataJpaTest to skip transaction
 */
@SpringBootTest
class MethodAnnotationITSpec extends Specification {

    @Autowired LeadRepository repository

    @Autowired
    @Subject LeadService service


    def "should create new Lead using save method and return Lead containing ID"() {
        given:
            Long leadId = service.createLeadUsingSaveMethod("VALID_COMMENT").getId()
        when:
            Lead sut = repository.findById(leadId).get()
        then:
            sut != null
            with(sut) {
                id == leadId
                comment == "VALID_COMMENT"
            }
    }

    /**
     * Move to separate testcase or clear DB before? Other test cases leave entities in DB !!!
     * @return
     */
    @Ignore
    def "should create new Lead using save method and return Lead containing ID 2"() {
        when:
            Long leadId = service.createLeadUsingAnnotation("VALID_COMMENT_CREATED_1").getId()
            List<Lead> sut1 = repository.findAll()
        then:
            leadId == null
            sut1.size() == 1
            with(sut1.get(0)) {
                comment == "VALID_COMMENT_CREATED_1"
            }
    }

    def "should update Lead using @Transactional annotation on public method"() {
        given:
            Long leadId = service.createLeadUsingSaveMethod("VALID_COMMENT").getId()
        when:
            service.updateByPublicMethod(leadId, "NEW_VALID_COMMENT")
            Lead sut = repository.getByIdOrThrow(leadId)
        then:
            sut != null
            with(sut) {
                id == leadId
                comment == "NEW_VALID_COMMENT"
            }
    }

    def "should skip update Lead using @Transactional annotation on package method"() {
        given:
            Long leadId = service.createLeadUsingSaveMethod("VALID_COMMENT").getId()
        when:
            service.updateByPackageMethod(leadId, "NEW_VALID_COMMENT")
            Lead sut = repository.getByIdOrThrow(leadId)
        then:
            sut != null
            with(sut) {
                id == leadId
                comment == "VALID_COMMENT"
            }
    }

    def "should skip Lead using @Transactional annotation on protected method"() {
        given:
            Long leadId = service.createLeadUsingSaveMethod("VALID_COMMENT").getId()
        when:
            service.updateByProtectedMethod(leadId, "NEW_VALID_COMMENT")
            Lead sut = repository.getByIdOrThrow(leadId)
        then:
            sut != null
            with(sut) {
                id == leadId
                comment == "VALID_COMMENT"
            }
    }

    def "should skip update without @Transactional annotation"() {
        given:
            Long leadId = service.createLeadUsingSaveMethod("VALID_COMMENT").getId()
        when:
            service.updateWithoutAnnotation(leadId, "NEW_VALID_COMMENT")
            Lead sut = repository.getByIdOrThrow(leadId)
        then:
            sut != null
            with(sut) {
                id == leadId
                comment == "VALID_COMMENT"
            }
    }

}
