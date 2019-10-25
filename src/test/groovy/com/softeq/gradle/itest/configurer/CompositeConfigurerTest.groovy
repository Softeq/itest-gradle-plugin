package com.softeq.gradle.itest.configurer

import com.softeq.gradle.itest.ItestSourceSetExtension
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class CompositeConfigurerTest extends Specification {

    def 'evaluate composite configurer'() {
        given:
        def ext = new ItestSourceSetExtension()
        def project = ProjectBuilder.builder().build()
        project.plugins.apply('java')
        project.plugins.apply('com.softeq.gradle.itest')
        def configurer = new CompositeConfigurer(project, ext)

        when:
        configurer.onProjectAfter()

        then:
        assert project.tasks.findByName('integrationTest') != null
        assert project.sourceSets.itest != null
    }

}
