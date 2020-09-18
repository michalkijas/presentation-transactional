package com.example.demo.sandbox

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
class RepositoryTransactionSpec extends Specification {

    @Autowired
    @Subject LeadRepository sut


    def setup() {
        sut.deleteAll()
    }


    def "should open Transaction when using SimpleJpaRepository.getOne()"() {
        given:
            Lead lead = sut.save(new Lead())
        when:
            Lead result = sut.getOne(lead.getId())
        then:
            noExceptionThrown()
            result.id != null
    }

    def "should open Transaction when using SimpleJpaRepository.findAll()"() {
        given:
            sut.save(new Lead())
        when:
            List<Lead> result = sut.findAll()
        then:
            noExceptionThrown()
            result.isEmpty() == false
    }

    def "should open Transaction when using custom repository method getByIdOrThrow"() {
        given:
            Lead lead = sut.save(new Lead())
        when:
            Lead result = sut.getByIdOrThrow(lead.getId())
        then:
            noExceptionThrown()
            result.id != null
    }

    def "should open Transaction when using method findByComment based on entity fields"() {
        given:
            sut.save(new Lead(comment: "VALID_COMMENT"))
        when:
            Lead result = sut.findByComment("VALID_COMMENT")
        then:
            noExceptionThrown()
            result.comment == "VALID_COMMENT"
    }

    def "should open Transaction when using method findAll with parameters of specification and page type"() {
        given:
            sut.save(new Lead(comment: "VALID_COMMENT"))
            org.springframework.data.jpa.domain.Specification<Lead> specification =
                    (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("comment"), "VALID_COMMENT")
            Pageable page = PageRequest.of(0, 10)
        when:
            Page<Lead> result = sut.findAll(specification, page)
        then:
            noExceptionThrown()
            result.totalElements == 1
    }

    def "should open Transaction when using SimpleJpaRepository.save()"() {
        when:
            Lead result = sut.save(new Lead())
        then:
            noExceptionThrown()
            result.id != null
    }

    def "should open Transaction when using SimpleJpaRepository.deleteById()"() {
        given:
            Lead lead = sut.save(new Lead())
        when:
            sut.deleteById(lead.getId())
        then:
            noExceptionThrown()
            sut.findById(lead.getId()).isPresent() == false
    }

}
