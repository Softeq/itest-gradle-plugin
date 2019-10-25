package com.softeq.gradle.itest.configurer

import com.softeq.gradle.itest.ItestSourceSetExtension
import org.gradle.api.Project

import static com.softeq.gradle.itest.utils.Tasks.configureDependsOn
import static com.softeq.gradle.itest.utils.Tasks.getTask

class TestTaskConfigurer implements Configurer {

    Project project
    ItestSourceSetExtension extension

    TestTaskConfigurer(Project project, ItestSourceSetExtension extension) {
        this.project = project
        this.extension = extension
    }

    @Override
    void onProjectAfter() {
        def itestSourceSet = project.sourceSets[extension.name]
        def testTask = getTask(project, 'integrationTest')

        testTask.testClassesDirs = itestSourceSet.output.classesDirs
        testTask.classpath = itestSourceSet.runtimeClasspath

        if (!project.hasProperty('disableIntegrationTests')) {
            configureDependsOn(project, 'check', 'integrationTest')
        }
    }

}
