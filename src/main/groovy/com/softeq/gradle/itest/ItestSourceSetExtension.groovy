package com.softeq.gradle.itest

class ItestSourceSetExtension {
    String name = 'itest'
    def compileClasspath = null
    def runtimeClasspath = null

    def retrieveCompileClasspath(sourceSets) {
        return compileClasspath ?: sourceSets.main.output + sourceSets.main.compileClasspath
    }

    def retrieveRuntimeClasspath(sourceSets) {
        return runtimeClasspath ?: sourceSets.main.output + sourceSets.main.runtimeClasspath
    }

    def isCustomDefinedName() {
        name != 'itest'
    }
}
