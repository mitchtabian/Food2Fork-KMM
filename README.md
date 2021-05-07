# Food2Fork Recipe App
<img src="https://github.com/mitchtabian/Food2Fork-KMM/blob/master/assets/food2fork_logo.png?raw=true" alt="Food2Fork App Icon" width="100" height="100">
This is the codebase for an upcoming course featuring Kotlin Multiplatform Mobile.

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

# Kotlin Multiplatform Talks
1. Daniele Barconcelli
	- https://www.youtube.com/watch?v=J3x7_HhrvO8
1. Ekaterina Petrova
	- https://www.youtube.com/watch?v=PW-jkOLucjM&ab_channel=JetBrainsTV
	- https://www.youtube.com/watch?v=mdN6P6RI__k
1. Dmitry Savvinov
	- https://www.youtube.com/watch?v=5QPPZV04-50&ab_channel=JetBrainsTV
1. Kevin Galligan
	- Kotlin conf 2019
		- https://www.youtube.com/watch?v=oxQ6e1VeH4M&ab_channel=JetBrainsTV
1. Ben Asher and Alec Strong
	- kotlin conf 2019
		- https://www.youtube.com/watch?v=je8aqW48JiA&ab_channel=JetBrainsTV









