/**
 * Created by AydbTiko on 12/03/2022.
 *
 */
internal val androidDependencies = listOf(
    Android.KTX,
    Android.APP_COMPAT,
    Android.MATERIAL,
    Android.LIFECYCLE,
    Android.COMPOSE_UI,
    Android.COMPOSE_MATERIAL,
    Android.COMPOSE_TOOLING,
    Android.ACTIVITY_COMPOSE,
    Android.HILT,
    Android.HILT_NAVIGATION,
    Android.ROOM_RUNTIME,
    Android.ROOM_KTX,
    Android.DATASTORE,
)

internal val compilers = listOf(
    Android.HILT_COMPILER,
    Android.ROOM_COMPILER,
    Moshi.compiler
)

internal val remoteDependencies = listOf(
    Moshi.dependency,
    Retrofit.dependency,
    Retrofit.converter
)

internal val loggingDependencies = listOf(
    Logging.OKHTTP,
    Logging.TIMBER
)

internal val unitTestDependencies = listOf(
    AndroidTest.ARC_CORE_TEST,
    AndroidTest.TEST_CORE,
    AndroidTest.ROOM,
    AndroidTest.HILT,
    KotlinTest.COROUTINES,
    Junit.dependency,
    Junit.ext,
    Junit.compose,
    Truth.dependency,
    Mockk.dependency,
    MockWebServer.dependency,
    Robolectric.dependency
)

internal val androidTestDependencies = listOf(
    AndroidTest.HILT,
    KotlinTest.COROUTINES,
    Junit.ext,
    Junit.compose,
    Espresso.dependency,
)