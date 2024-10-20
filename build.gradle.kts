import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

plugins {
    id("com.android.application") version "8.5.0" apply true
    id("org.jetbrains.kotlin.android") version "1.8.0" apply true
}

android {
    namespace = "com.example.venuevista" // Set your app's namespace

    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.venuevista"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    // AndroidX dependencies
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Kotlin dependencies
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")

    // Retrofit (for networking)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Testing dependencies
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") // Update to latest version
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // JUnit 4 for Android

    // Unit testing dependencies
    testImplementation("junit:junit:4.13.2")

    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("androidx.annotation:annotation:1.6.0")
    // other dependencies...
}
