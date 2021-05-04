pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
}
enableFeaturePreview("GRADLE_METADATA")
rootProject.name = "Food2Fork_KMM"
include(":androidFood2Fork")
include(":shared")