plugins{
    id("io.freefair.lombok")
}

dependencies{
    implementation("org.springframework.boot:spring-boot-starter-web"){
        exclude("org.springframework.boot", "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")

    testImplementation("org.springframework.boot:spring-boot-starter-test")


}

tasks{
    test{
        useJUnitPlatform()
    }
}