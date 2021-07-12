object Build {
    private const val gradleBuildTools = "7.1.0-alpha03"
    const val buildTools = "com.android.tools.build:gradle:${gradleBuildTools}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    const val sqlDelightGradlePlugin = "com.squareup.sqldelight:gradle-plugin:${SQLDelight.sqlDelightVersion}"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Hilt.version}"
}