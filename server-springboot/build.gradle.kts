plugins{
    id("org.springframework.boot")
}

dependencies{
    implementation("org.springframework.boot:spring-boot-starter-web"){
        exclude("org.springframework.boot", "spring-boot-starter-tomcat")
    }

    implementation("org.springframework.boot:spring-boot-starter-undertow")
}