package com.softeq.gradle.itest.common

import com.softeq.gradle.itest.ItestSourceSetExtension
import com.softeq.gradle.itest.configurer.SourceSetConfigurer
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

abstract class Projects {

    static Project makeProjectAndSourceSet() {
        def project = makeProject()

        configureSourceSet(project)

        project
    }

    static Project makeProject() {
        def project = ProjectBuilder.builder().build()

        project.plugins.apply('java')
        project.plugins.apply('com.softeq.gradle.itest')

        project
    }

    static void configureSourceSet(Project project) {
        def ext = new ItestSourceSetExtension()
        def sourceSetConfigurer = new SourceSetConfigurer(project, ext)

        sourceSetConfigurer.onProjectAfter()
    }

}