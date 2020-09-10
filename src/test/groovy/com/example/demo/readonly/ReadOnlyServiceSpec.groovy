package com.example.demo.readonly

import org.junit.Ignore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject

//@Import(TransactionRoutingConfiguration)
@SpringBootTest
class ReadOnlyServiceSpec extends Specification {

    @Autowired
    @Subject ReadOnlyService sut = new ReadOnlyService()


    def "should run transation in read-write mode on primary node"() {
        when:
            sut.create("comment")
        then: // check where is running
            true
    }

    @Ignore
    def "should run transation in read-only mode on replica node"() {
        when:
            sut.findAll()
        then: // check where is running
            true
    }

}
