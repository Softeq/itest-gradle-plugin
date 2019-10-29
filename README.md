## Gradle Integration Testing Plugin
[![Build Status](https://travis-ci.org/Softeq/itest-gradle-plugin.svg?branch=master)](https://travis-ci.org/Softeq/itest-gradle-plugin)
[![Coverage Status](https://coveralls.io/repos/github/Softeq/itest-gradle-plugin/badge.svg?branch=master)](https://coveralls.io/github/Softeq/itest-gradle-plugin?branch=master)
[![Gradle Plugin](https://img.shields.io/maven-metadata/v.svg?label=gradle&metadataUrl=https://plugins.gradle.org/m2/com/softeq/gradle/itest/com.softeq.gradle.itest.gradle.plugin/maven-metadata.xml)](https://plugins.gradle.org/plugin/com.softeq.gradle.itest)

There are plugin that allow to configure integration tests same way as unit tests in simple way

## Build

To build the project it is require to execute 
```bash
./gradlew clean build 
```

## Documentation

Content:
1. [Quick Start](#quick-start)
2. [Source Set](#source-set)
3. [Integration Test Task](#integration-test-task)
    - [JUnit 5](#junit-5)
    - [TestNG](#testng)
4. [Dependencies Management](#dependencies-management)
5. [Skip Tests](#skip-tests)
6. [Configurable Parameters](#configurable-parameters)

### Quick Start

To add ***itest*** plugin to your project it will require to add next code to the `build.gradle`
```groovy
plugins {
    id 'com.softeq.gradle.itest' version '1.0.3'
}
```

Or with Kotlin
```kotlin
plugins {
  id("com.softeq.gradle.itest") version "1.0.3" 
}
```

After that you will have possibilities to write tests to the **itest/** folder.

To run the tests you just need to execute in your project 
```bash
./gradlew clean build
```

### Source Set

There are by default application add new source set to the project with name `itest`.
To change name of the source set it is possible to use `name` configuration parameter. In this case location of 
integration test sources will be at folder with specified name.

With Groovy / Kotlin
```groovy
itestSourceSet {
    name = "integrationTest"
}
``` 

Also there are possible to customize compile classpath and runtime classpath of the source set

Groovy
```groovy
itestSourceSet {
    name = "integrationTest"
    compileClasspath = sourceSets.main.compileClasspath
    runtimeClasspath = sourceSets.main.runtimeClasspath
}
```

Kotlin 
```kotlin
itestSourceSet {
    name = "integrationTest"
    compileClasspath = sourceSets["main"].compileClasspath
    runtimeClasspath = sourceSets["main"].runtimeClasspath
}
```

### Integration Test Task

Current plugin also configure task `integrationTest` that extends standard `Test` task of the Gradle.
Configuration parameters for this task you may find 
[there](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.testing.Test.html)

#### JUnit 5
To add support of JUnit 5 you will require to specify at configuration task 

Groovy
```groovy
integrationTask {
    useJUnitPlatform()
}
```

Kotlin 
```kotlin
tasks.withType<Test> {
    useJUnitPlatform()
}
```

#### TestNG
To use TestNG for integration testing of you application it will require to specify next configuration for the
`integrationTest` task

Groovy
```groovy
integrationTest {
    useTestNG()
}
```

Kotlin 
```kotlin
tasks.withType<Test> {
    useTestNG()
}
```

### Dependencies Management

There are possible to specify additional dependencies for the integration test classes

| Standard Configuration| itest Configuration  | Description                       |
|-----------------------|----------------------|-----------------------------------|
| implementation        | itestImplementation  | Implementation dependencies scope |
| compileOnly           | itestCompileOnly     | Compile Only dependencies scope   |
| runtimeOnly           | itestRuntimeOnly     | Runtime Only dependencies scope   |

You can specify this dependencies in the `dependencies` section of the `build.gradle` file

Groovy
```groovy
dependencies {
    itestRuntimeOnly 'com.h2database:h2:1.0.60'
}
``` 

Kotlin
```kotlin
dependencies {
    itestRuntimeOnly("com.h2database:h2:1.0.60")
}
```

### Skip Tests

To skip integration tests you need to provide `-PdisableIntegrationTests` option to the gradle.

For instance
```bash
gradlew clean build -PdisableIntegrationTests
```

Alternative options to disable integration and unit tests you can find below

```bash
gradlew clena build -x test -x integrationTest
```

### Configurable Parameters

There are table with available plugin configuration parameters

| Param            | Parent Configuration | Default Value | Description                       |
|------------------|----------------------|---------------|-----------------------------------|
| name             | itestSourceSet       | "itest"       | There are name of the folder with integration test sources |
| compileClasspath | itestSourceSet       | Main SourceSet output and classpath | There are classpath of the compiler to build integration tests |
| runtimeClasspath | itestSourceSet       | Main SourceSet output and runtime classpath | There are runtime classpath that will be used during integration tests evaluation | 
| [Gradle Test Params](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.testing.Test.html) | integrationTest | - | There are standard Gradle Test task configuration |
| useJUnitPlatform() | integrationTest | - | There are option to enable JUnit 5 tests execution |
| useTestNG()        | integrationTest | - | There are option to enable TestNG evaluation support |

## License

MIT
