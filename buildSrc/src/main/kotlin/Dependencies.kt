/**
 * Created by AydbTiko on 12/03/2022.
 *
 */
/**
 * Gradle Dependencies
 */
object Gradle {

    const val BUILD_TOOLS = "com.android.tools.build:gradle:7.1.2"
}

/**
 * Kotlin Dependencies
 */
object Kotlin {

    private const val VERSION = "1.6.10"
    const val PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$VERSION"
    const val coroutinesVersion = "1.5.2"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
}

/**
 * Android Dependencies
 */
object Android {

    const val KTX = "androidx.core:core-ktx:1.7.0"
    const val APP_COMPAT = "androidx.appcompat:appcompat:1.4.1"
    const val MATERIAL = "com.google.android.material:material:1.5.0"

    // compose
    const val composeVersion = "1.1.1"
    const val COMPOSE_UI = "androidx.compose.ui:ui:$composeVersion"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:$composeVersion"
    const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:1.4.0"

    // lifecycle
    const val LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"

    // hilt
    const val hiltVersion = "2.40.5"
    const val HILT = "com.google.dagger:hilt-android:$hiltVersion"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    const val HILT_NAVIGATION = "androidx.hilt:hilt-navigation-compose:1.0.0"
    const val HILT_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"

    // room
    const val roomVersion = "2.4.2"
    const val ROOM_RUNTIME = "androidx.room:room-runtime:$roomVersion"
    const val ROOM_KTX = "androidx.room:room-ktx:$roomVersion"
    const val ROOM_COMPILER = "androidx.room:room-compiler:$roomVersion"

    // data store
    const val DATASTORE = "androidx.datastore:datastore-preferences:1.0.0"

}

/**
 * Moshi
 */
object Moshi {

    private const val version = "1.13.0"
    const val dependency = "com.squareup.moshi:moshi-kotlin:$version"
    const val compiler = "com.squareup.moshi:moshi-kotlin-codegen:$version"
}

/**
 * Retrofit
 */
object Retrofit {

    private const val version = "2.9.0"
    const val dependency = "com.squareup.retrofit2:retrofit:$version"
    const val converter = "com.squareup.retrofit2:converter-moshi:$version"
}

/**
 * Logging
 */
object Logging {

    const val OKHTTP = "com.squareup.okhttp3:logging-interceptor:4.9.1"
    const val TIMBER = "com.jakewharton.timber:timber:4.7.1"
}

/**
 * Lottie
 */
object Lottie {

    const val dependency = "com.airbnb.android:lottie-compose:4.0.0"
}
