import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin(KotlinPlugins.multiplatform)
    kotlin(KotlinPlugins.cocoapods)
    kotlin(KotlinPlugins.serialization) version Kotlin.version
    id(Plugins.androidLibrary)
    id(Plugins.sqlDelight)
}

version = "1.0"

android {
    compileSdk = Application.compileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "shared"
        }
        podfile = project.file("../iosFood2Fork/Podfile")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies{
                implementation(Ktor.core)
                implementation(Ktor.jsonSerialization)
                implementation(Ktor.contentNegotiation)
                implementation(Kotlinx.datetime)
                implementation(SQLDelight.runtime)
            }
        }
        val androidMain by getting {
            dependencies{
                implementation(Ktor.clientCIO)
                implementation(SQLDelight.androidDriver)
            }
        }
        val iosMain by getting{
            dependencies {
                implementation(Ktor.clientDarwin)
                implementation(SQLDelight.nativeDriver)
            }
        }
    }
}

sqldelight {
    database("RecipeDatabase") {
        packageName = "com.codingwithmitch.food2forkkmm.datasource.cache"
        sourceFolders = listOf("sqldelight")
    }
}






