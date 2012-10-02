package com.jordon

import grails.plugin.spock.IntegrationSpec;

import org.grails.dynamicdomain.DynamicDomainService;

class DynamicDomainServiceSpec extends IntegrationSpec {
	def grailsApplication
	def dynamicDomainService
	def domainClass = '''public class DDSTest {
		String name
		String value
		static constraints = {
			name( nullable:false )
			value( nullable:false )
		}
}
'''
	def "test dynamic domain class creation"() {
		setup:
		dynamicDomainService.registerDomainClass( domainClass )
		
		when:
		def dc = grailsApplication.getClassForName( 'DDSTest' ).newInstance()
		dc.name = name
		dc.value = value
		dc.validate()
		
		then:
		dc.hasErrors() != valid
		
		where:
		name			| value			| valid
		'TestName'		| 'TestValue'	| true
		null			| null			| false
		'TestName'		| null			| false
		null			| 'TestValue'	| false
	}
	
	def "test alternate config file"() {
		when:
		// def prop1 = grailsApplication.config.jordon.config.property1
		def prop1 = grailsApplication.config.jordon.config.property1
		
		then:
		prop1 == true
	}
}
