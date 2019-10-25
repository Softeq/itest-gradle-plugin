package com.softeq.gradle.itest.configurer

import com.softeq.gradle.itest.ItestSourceSetExtension
import org.gradle.api.Project

class CompositeConfigurer implements Configurer {

    def configurers = []

    CompositeConfigurer(Project project, ItestSourceSetExtension extension) {
        configurers <<= new SourceSetConfigurer(project, extension)
        configurers <<= new TestTaskConfigurer(project, extension)
    }

    @Override
    void onProjectAfter() {
        configurers.each { it.onProjectAfter() }
    }

}
