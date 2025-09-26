import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true

            // Export libs you want to access from Swift
//            export(libs.ktor.client.core)
//            export(libs.ktor.client.darwin)
//            export("io.github.reactivecircus.cache4k:cache4k:0.13.0")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Compose multiplatform (safe for Android & iOS)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                // Ktor multiplatform
                implementation(libs.ktor.client.core)
                implementation("io.ktor:ktor-client-content-negotiation:${libs.versions.ktor.get()}")
                implementation("io.ktor:ktor-serialization-kotlinx-json:${libs.versions.ktor.get()}")
                implementation("io.ktor:ktor-client-logging:${libs.versions.ktor.get()}")

                // Coroutines & Serialization
                implementation(libs.kotlinx.coroutines.core)
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

                // Okio
                implementation("com.squareup.okio:okio:3.9.0")

                implementation("io.github.reactivecircus.cache4k:cache4k:0.13.0")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)

                // Lifecycle (Android-only)
                implementation(libs.androidx.lifecycle.viewmodelCompose)
                implementation(libs.androidx.lifecycle.runtimeCompose)

                // Android Ktor engine
                implementation(libs.ktor.client.okhttp)

                implementation("androidx.compose.runtime:runtime-livedata:1.5.0")
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                // iOS Ktor engine
                implementation(libs.ktor.client.darwin)
            }
        }

        val iosArm64Main by getting { dependsOn(iosMain) }
        val iosSimulatorArm64Main by getting { dependsOn(iosMain) }
    }
}

android {
    namespace = "com.appwork.pokeapi_kmm"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.appwork.pokeapi_kmm"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

