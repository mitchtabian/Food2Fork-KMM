buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
        maven ( url = "https://www.jetbrains.com/intellij-repository/releases" )
        maven ( url = "https://jetbrains.bintray.com/intellij-third-party-dependencies" )
    }
    dependencies {
        classpath(Build.kotlinGradlePlugin)
        classpath(Build.buildTools)
        classpath(Build.sqlDelightGradlePlugin)
        classpath(Build.hiltGradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}