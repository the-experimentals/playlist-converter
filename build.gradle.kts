plugins {
    kotlin("jvm") version "1.9.0"
    id("io.spring.dependency-management") version "1.1.2"
    id("org.springframework.boot") version "3.1.1" apply false
    id("com.github.node-gradle.node") version "5.0.0" apply false
    id("io.freefair.lombok") version "8.1.0" apply false
}

allprojects {
    group = "com.playlist.converter"

    apply {
        plugin("io.spring.dependency-management")
    }

    afterEvaluate {
        dependencyManagement{
            imports {
                mavenBom("org.springframework.boot:spring-boot-dependencies:3.1.1")

            }

            dependencies{
                dependency("com.squareup.okhttp3:okhttp:4.11.0")
                dependency("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.15.2")
            }
        }
    }
}