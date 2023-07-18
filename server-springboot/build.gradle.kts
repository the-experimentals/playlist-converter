plugins{
    kotlin("jvm")
    id("org.springframework.boot")
}

dependencies{

    runtimeOnly(project(":clientapp-react"))

    implementation("org.springframework.boot:spring-boot-starter-web"){
        exclude("org.springframework.boot", "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

}

tasks{

    test{
        useJUnitPlatform()
        dependsOn("copyWebApp")
    }

    create<Copy>("copyWebApp"){
        from("$rootDir/clientapp-react/build")
        into("$rootDir/server-springboot/build/resources/main/static/.")
    }

    bootJar{
        dependsOn("copyWebApp")
    }

    compileKotlin{
        dependsOn(":clientapp-react:build")
    }

    resolveMainClassName{
        dependsOn("copyWebApp")
    }

    jar{
        dependsOn("copyWebApp")
    }
}