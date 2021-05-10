object Hilt {
    const val version = "2.35"
    const val hiltAndroid = "com.google.dagger:hilt-android:$version"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:$version"

    private const val hiltNavigationComposeVersion = "1.0.0-alpha02"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"

    private const val hiltNavigationVersion = "1.0.0-alpha03"
    const val hiltNavigation = "androidx.hilt:hilt-navigation:$hiltNavigationVersion"
}