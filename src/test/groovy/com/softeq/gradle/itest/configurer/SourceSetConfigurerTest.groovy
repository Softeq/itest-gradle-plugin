package com.softeq.gradle.itest.configurer

import com.softeq.gradle.itest.ItestSourceSetExtension
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class SourceSetConfigurerTest extends Specification {

    def 'apply source set configurer'() {
        given:
        def ext = new ItestSourceSetExtension()
        def project = ProjectBuilder.builder().build()
        project.plugins.apply('java')
        def configurer = new SourceSetConfigurer(project, ext)

        when:
        configurer.onProjectAfter()

        then:
        assert project.sourceSets.itest != null
    }

}
