package com.softeq.gradle.itest

import com.softeq.gradle.itest.configurer.CompositeConfigurer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

class ItestPlugin implements Plugin<Project> {

    void apply(Project project) {
        def extension = project.extensions.create('itestSourceSet', ItestSourceSetExtension)

        project.sourceSets.create('itest', { })

        project.configurations {
            itestImplementation.extendsFrom implementation
            itestRuntimeOnly.extendsFrom runtimeOnly
            itestCompileOnly.extendsFrom compileOnly
        }

        project.task('integrationTest', type: Test) {
            description = 'Runs the integration tests.'
            group = 'verification'

            outputs.upToDateWhen { false }
            mustRunAfter 'test'
        }

        def configurer = new CompositeConfigurer(project, extension)
        project.afterEvaluate { configurer.onProjectAfter() }
    }

}
