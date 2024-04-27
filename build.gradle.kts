plugins{
    base
    kotlin("jvm") version "1.9.23"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.github.node-gradle.node") version "7.0.2" apply false
    id("io.freefair.lombok") version "8.6" apply false
    id("org.springframework.boot") version "3.2.5" apply false
}

allprojects {
    group = "com.playlist.converter"

    apply {
        plugin("base")
        plugin("io.spring.dependency-management")
    }

    afterEvaluate {
        dependencyManagement{
            imports {
                mavenBom("org.springframework.boot:spring-boot-dependencies:3.2.5")
            }

            dependencies {
                dependency("com.squareup.okhttp3:okhttp:4.12.0")
            }
        }
    }
}