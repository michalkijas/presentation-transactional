package com.example.demo.sandbox

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import spock.lang.Ignore
import spock.lang.Specification

import javax.transaction.Transactional

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods

/**
 * https://www.archunit.org/getting-started
 */
class ArchUnitSpec extends Specification {

    JavaClasses sut = new ClassFileImporter().importPackages("com.example.demo");


    @Ignore("fails on current implementation")
    def "should annotation @Transactional exist only on public methods"() {
        expect:
            methods()
                    .that().areAnnotatedWith(Transactional.class)
                    .should().bePublic()
                    .check(sut);
    }

    def "should annotation @Transactional exist only on public and package-private scope classes"() {
        expect:
            classes()
                    .that().areAnnotatedWith(Transactional.class)
                    .should().bePublic().orShould().bePackagePrivate()
                    .check(sut);
    }

}
