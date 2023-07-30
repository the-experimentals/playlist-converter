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

include(":converter-service")
include(":clientapp-react")
