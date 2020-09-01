package com.example.demo.arch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.elements.MethodsShouldConjunction;
import org.junit.jupiter.api.Test;
import javax.transaction.Transactional;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

/**
 * https://www.archunit.org/getting-started
 */
public class ArchTest {

    @Test
    public void archTest() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.example.demo");

        MethodsShouldConjunction rule = methods()
                .that().areAnnotatedWith(Transactional.class)
                .should().bePublic();

        rule.check(importedClasses);
    }

}
