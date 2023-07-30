plugins{
    base
    kotlin("jvm") version "1.9.0"
    id("io.spring.dependency-management") version "1.1.2"
    id("com.github.node-gradle.node") version "5.0.0" apply false
    id("io.freefair.lombok") version "8.1.0" apply false
    id("org.springframework.boot") version "3.1.2" apply false
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
                mavenBom("org.springframework.boot:spring-boot-dependencies:3.1.2")
            }

            dependencies {

            }
        }
    }
}