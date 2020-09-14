package com.example.demo.sandbox

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject

/**
 * Using @SpringBootTest instead of @DataJpaTest to skip transaction
 *
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#transaction-declarative-attransactional-settings
 * Method visibility and @Transactional
 * When you use proxies, you should apply the @Transactional annotation only to methods with public visibility.
 * If you do annotate protected, private or package-visible methods with the @Transactional annotation, no error is raised,
 * but the annotated method does not exhibit the configured transactional settings.
 * If you need to annotate non-public methods, consider using AspectJ (described later).
 *
 * You can apply the @Transactional annotation to an interface definition, a method on an interface, a class definition, or a public method on a class.
 *
 * Alternative:
 * @Import (LeadConfiguration.class)
 * @DataJpaTest
 */
@SpringBootTest
class PublicClassTransactionServiceSpec extends Specification {

    @Autowired LeadRepository repository

    @Autowired
    @Subject PublicClassTransactionService sut


    def "should commit changes when using annotation on public class level and method with public scope"() {
        given:
            Long leadId = sut.createLeadUsingSaveMethod("VALID_COMMENT").getId()
        when:
            sut.updateByPublicMethod(leadId, "NEW_VALID_COMMENT")
            Lead lead = repository.getByIdOrThrow(leadId)
        then:
            with(lead) {
                id == leadId
                comment == "NEW_VALID_COMMENT"
            }
    }

    def "should drop changes when using annotation on public class level and method with package scope"() {
        given:
            Long leadId = sut.createLeadUsingSaveMethod("VALID_COMMENT").getId()
        when:
            sut.updateByPackageMethod(leadId, "NEW_VALID_COMMENT")
            Lead lead = repository.getByIdOrThrow(leadId)
        then:
            with(lead) {
                id == leadId
                comment == "VALID_COMMENT"
            }
    }

    def "should drop changes when using annotation on public class level and method with protected scope"() {
        given:
            Long leadId = sut.createLeadUsingSaveMethod("VALID_COMMENT").getId()
        when:
            sut.updateByProtectedMethod(leadId, "NEW_VALID_COMMENT")
            Lead lead = repository.getByIdOrThrow(leadId)
        then:
            with(lead) {
                id == leadId
                comment == "VALID_COMMENT"
            }
    }

}
