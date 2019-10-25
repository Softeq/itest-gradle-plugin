package com.softeq.gradle.itest.utils

import org.gradle.api.Project

abstract class Tasks {

    static configureDependsOn(Project project, String dependentTaskName, String sourceTaskName) {
        def dependentTask = getTask(project, dependentTaskName)
        def sourceTask = getTask(project, sourceTaskName)

        dependentTask.dependsOn sourceTask
    }

    static getTask(Project project, String name) {
        return project
            .tasks
            .matching { it.name == name } [0]
    }

}
