plugins {
    kotlin("jvm") version "1.9.0"
    id("io.spring.dependency-management") version "1.1.2"
    id("org.springframework.boot") version "3.1.1" apply false
}

allprojects {
    group = "com.playlist.converter"

    apply {
        plugin("kotlin")
        plugin("io.spring.dependency-management")
    }

    afterEvaluate {
        dependencyManagement{
            imports {
                mavenBom("org.springframework.boot:spring-boot-dependencies:3.1.1")

            }

            dependencies{

            }
        }
    }
}