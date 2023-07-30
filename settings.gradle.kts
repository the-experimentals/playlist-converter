pluginManagement{
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "playlist-converter"

include(":server-springboot")
include(":clientapp-react")
