package com.softeq.gradle.itest.configurer

import com.softeq.gradle.itest.ItestSourceSetExtension
import com.softeq.gradle.itest.common.Projects
import spock.lang.Specification

class CompositeConfigurerTest extends Specification {

    def 'evaluate composite configurer'() {
        given:
        def ext = new ItestSourceSetExtension()
        def project = Projects.makeProject()
        def configurer = new CompositeConfigurer(project, ext)

        when:
        configurer.onProjectAfter()

        then:
        assert project.tasks.findByName('integrationTest') != null
        assert project.sourceSets.itest != null
    }

}
