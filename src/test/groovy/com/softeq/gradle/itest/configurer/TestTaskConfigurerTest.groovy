package com.softeq.gradle.itest.configurer

import com.softeq.gradle.itest.ItestSourceSetExtension
import org.gradle.api.Project
import spock.lang.Specification

import static com.softeq.gradle.itest.common.Projects.makeProjectAndSourceSet

class TestTaskConfigurerTest extends Specification {

    def 'reconfigure integrationTest task'() {
        given:
        def ext = new ItestSourceSetExtension()
        Project project = makeProjectAndSourceSet()
        def configurer = new TestTaskConfigurer(project, ext)

        when:
        configurer.onProjectAfter()

        then:
        def integrationTestTask = project.tasks.findByName('integrationTest')
        def itestSourceSet = project.sourceSets.itest

        assert itestSourceSet.runtimeClasspath == integrationTestTask.classpath
    }

}
