package com.softeq.gradle.itest.configurer

import com.softeq.gradle.itest.ItestSourceSetExtension
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class TestTaskConfigurerTest extends Specification {

    def 'reconfigure integrationTest task'() {
        given:
        def ext = new ItestSourceSetExtension()
        Project project = makeProject()
        def configurer = new TestTaskConfigurer(project, ext)

        when:
        configurer.onProjectAfter()

        then:
        def integrationTestTask = project.tasks.findByName('integrationTest')
        def itestSourceSet = project.sourceSets.itest

        assert itestSourceSet.runtimeClasspath == integrationTestTask.classpath
    }

    private static Project makeProject() {
        def project = ProjectBuilder.builder().build()

        project.plugins.apply('java')
        project.plugins.apply('com.softeq.gradle.itest')

        configureSourceSet(project)

        project
    }

    private static void configureSourceSet(Project project) {
        def ext = new ItestSourceSetExtension()
        def sourceSetConfigurer = new SourceSetConfigurer(project, ext)

        sourceSetConfigurer.onProjectAfter()
    }

}
