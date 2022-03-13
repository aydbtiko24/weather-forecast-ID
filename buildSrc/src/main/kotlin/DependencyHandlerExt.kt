import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addCommonDependencies() {
    androidDependencies
        .plus(Kotlin.COROUTINES)
        .plus(remoteDependencies)
        .plus(loggingDependencies)
        .plus(Lottie.dependency)
        .forEach { add("implementation", it) }
}

fun DependencyHandler.addCompilerDependencies() {
    compilers.forEach { add("kapt", it) }
}

fun DependencyHandler.addTestDependencies() {
    unitTestDependencies.forEach {
        add("testImplementation", it)
    }
}

fun DependencyHandler.addAndroidTestsDependencies() {
    add("kaptAndroidTest", AndroidTest.HILT)
    androidTestDependencies.forEach {
        add("androidTestImplementation", it)
    }
}
