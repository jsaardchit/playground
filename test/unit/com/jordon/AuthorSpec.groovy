package com.jordon

import grails.test.mixin.*
import org.junit.*
import spock.lang.Specification
import grails.plugin.spock.UnitSpec
import grails.test.mixin.support.GrailsUnitTestMixin

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Author)
class AuthorSpec extends UnitSpec {
    def "find author by name"() {
        setup:
        mockDomain( Author )

        when:
        new Author( name:name, age:32  ).save()

        then:
        Author.findByName( name ) != null

        where:
        name = 'Jordon'
    }

    def "constraints test"() {
        setup:
        mockForConstraintsTests(Author)

        when:
        def author = new Author( name:name, age:age )
        author.validate()

        then:
        author.hasErrors() != valid

        where:
        name            | age   | valid
        null            | 18    | false
        ""              | 18    | false
        "a" * 100       | 18    | false
        "Jordon"        | 18    | true
    }
}
