plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android") // If using Kotlin for Android
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.venuevista"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    // Retrofit core library
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Retrofit converter for JSON (Gson converter)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp logging interceptor (optional for better debugging)
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

    // Other dependencies in your project (if any)
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Testing libraries
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
