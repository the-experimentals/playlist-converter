plugins{
    kotlin("jvm")
    id("io.freefair.lombok")
    id("org.springframework.boot")
}

dependencies{
    runtimeOnly(project(":clientapp-react"))
    implementation("org.springframework.boot:spring-boot-starter-web"){
        exclude("org.springframework.boot", "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("com.squareup.okhttp3:okhttp")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

}

tasks{
    test{
        useJUnitPlatform()
        dependsOn("copyWebApp")
    }

    create<Copy>("copyWebApp"){
        from("$rootDir/clientapp-react/build")
        into("$rootDir/converter-service/build/resources/main/static/.")
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
