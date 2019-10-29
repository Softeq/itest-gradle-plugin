package com.softeq.gradle.itest.configurer

import com.softeq.gradle.itest.ItestSourceSetExtension
import spock.lang.Specification

import static com.softeq.gradle.itest.common.Projects.makeProject

class SourceSetConfigurerTest extends Specification {

    def 'apply source set configurer'() {
        given:
        def ext = new ItestSourceSetExtension()
        def project = makeProject()
        def configurer = new SourceSetConfigurer(project, ext)

        when:
        configurer.onProjectAfter()

        then:
        assert project.sourceSets.itest != null
    }

    def 'apply source set configurer for source set with custom name'() {
        given:
        def ext = new ItestSourceSetExtension(name: 'integration')
        def project = makeProject()
        def configurer = new SourceSetConfigurer(project, ext)

        when:
        configurer.onProjectAfter()

        then:
        assert project.sourceSets.integration != null
    }

}
