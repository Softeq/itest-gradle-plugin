package com.softeq.gradle.itest.configurer

import com.softeq.gradle.itest.ItestSourceSetExtension
import org.gradle.api.Project

class SourceSetConfigurer implements Configurer {

    Project project;
    ItestSourceSetExtension extension;

    SourceSetConfigurer(Project project, ItestSourceSetExtension extension) {
        this.project = project
        this.extension = extension
    }

    @Override
    void onProjectAfter() {
        def sourceSets = project.sourceSets

        sourceSets.create(extension.name, {
            compileClasspath += extension.retrieveCompileClasspath(sourceSets)
            runtimeClasspath += extension.retrieveRuntimeClasspath(sourceSets)
        })
    }

}
