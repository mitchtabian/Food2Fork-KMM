# Important Configuration Information
Important Android Studio and gradle configuration information for this project.

### Android Studio Version
I've been using Canary 8. I was using a newer version but had some issue. You can see the version archive here: [Android Studio Archive](https://developer.android.com/studio/archive)

### Kotlin Multiplatform Mobile Plugin
Make sure you install this plugin. It will make your life way easier.

The Kotlin Multiplatform Mobile (KMM) plugin makes it easier to develop a Kotlin Multiplatform Project in Android Studio.

Install here: [Kotlin Multiplatform Mobile Plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile)

### build.gradle (shared)
Make sure your **shared** `build.gradle` contains this `configurations` block in the `android{...}` configuration section. The "getting started" guide on the kotlinlang.org website used to contain this but it doesn't anymore for some reason. Maybe it is not needed for newer versions of Canary. I'm not sure. But my projects do not build without it.

**build.gradle.kts (shared)**
```kotlin
... stuff

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
    }
    configurations { // <--- This
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

... more stuff
```

### Kotlin Version
I've been using kotlin version 1.4.31 since that is required for Jetpack Compose at this time.

Make sure you have this setting in android studio:

<img class='header-img' src='https://raw.githubusercontent.com/mitchtabian/Food2Fork-KMM/master/assets/Kotlin_android_studio.png' />










