package com.softeq.gradle.itest

import spock.lang.Specification

class ItestSourceSetExtensionTest extends Specification {

    def sourceSet = ['main': [
        'output': 'output + ',
        'compileClasspath': 'classpath',
        'runtimeClasspath': 'runtime'
    ]]

    def 'test compile classpath'() {
        given:
        def ext = new ItestSourceSetExtension()

        when:
        ext.compileClasspath = '1'

        then:
        assert ext.retrieveCompileClasspath(sourceSet) == '1'
    }

    def 'test compile classpath for default'() {
        given:
        def ext = new ItestSourceSetExtension()

        when:
        def result = ext.retrieveCompileClasspath(sourceSet)

        then:
        assert result == 'output + classpath'
    }

    def 'test runtime classpath configuration'() {
        given:
        def ext = new ItestSourceSetExtension()
        ext.runtimeClasspath = '1'

        when:
        def result = ext.retrieveRuntimeClasspath(sourceSet)

        then:
        assert result == '1'
    }

    def 'test runtime classpath configuration for default'() {
        given:
        def ext = new ItestSourceSetExtension()

        when:
        def result = ext.retrieveRuntimeClasspath(sourceSet)

        then:
        assert result == 'output + runtime'
    }

    def 'test set source set name'() {
        given:
        def ext = new ItestSourceSetExtension()

        when:
        ext.name = 'itg'

        then:
        assert ext.name == 'itg'
    }

}
