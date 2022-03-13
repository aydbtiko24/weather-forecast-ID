/**
 * Created by AydbTiko on 12/03/2022.
 *
 */
object AndroidTest {

    // core
    private const val acrTestingVersion = "2.1.0"
    const val ARC_CORE_TEST = "androidx.arch.core:core-testing:$acrTestingVersion"

    //
    private const val androidTestVersion = "1.4.0"
    const val TEST_CORE = "androidx.test:core:$androidTestVersion"

    // room
    const val ROOM = "androidx.room:room-testing:${Android.roomVersion}"

    // hilt
    const val HILT = "com.google.dagger:hilt-android-testing:${Android.hiltVersion}"
}

object KotlinTest {

    // coroutines
    const val COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Kotlin.coroutinesVersion}"
}

object Junit {

    // junit
    const val dependency = "junit:junit:4.13.2"
    private const val extJunitVersion = "1.1.3"
    const val ext = "androidx.test.ext:junit:$extJunitVersion"
    const val compose = "androidx.compose.ui:ui-test-junit4:${Android.composeVersion}"
}

object Truth {

    // truth
    const val dependency = "com.google.truth:truth:1.1.3"
}

object Espresso {

    // espresso
    const val dependency = "androidx.test.espresso:espresso-core:3.4.0"
}

object Mockk {

    // mockk
    const val dependency = "io.mockk:mockk:1.12.3"
}

object Robolectric {

    // robolectric
    const val dependency = "org.robolectric:robolectric:4.7.3"
}

object MockWebServer {

    // mock http request
    const val dependency = "com.squareup.okhttp3:mockwebserver:4.9.1"
}

