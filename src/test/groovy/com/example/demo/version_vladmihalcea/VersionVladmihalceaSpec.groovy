package com.example.demo.version_vladmihalcea

import com.example.demo.sandbox.ReadOnlyFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
class VersionVladmihalceaSpec extends Specification {

    @Autowired
    @Subject ReadOnlyFacade sut

    def "should run readOnly transaction"() {
        expect:
            sut.findAll() == null
    }

}
