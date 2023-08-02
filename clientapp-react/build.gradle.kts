import com.github.gradle.node.npm.task.NpmTask

plugins{
    id("com.github.node-gradle.node")
}

tasks{
    create<NpmTask>("appNpmBuild"){
        args.set(listOf("run","build"))
    }

    assemble{
        dependsOn("appNpmBuild")
    }
}