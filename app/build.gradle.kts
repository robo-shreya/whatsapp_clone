plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    kotlin("kapt")
}

android {
    namespace = "com.example.whatsAppClone"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.whatsAppClone"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
}

    dependencies {
        // Hilt
        implementation(libs.hilt.android)

        // Project modules
        implementation(project(":feature:chat"))
        implementation(project(":feature:conversations"))
        implementation(project(":feature:create_chat"))
        implementation(project(":common:framework"))
        implementation(project(":common:data"))

        // Navigation
        implementation(libs.androidx.navigation.compose)

        // Core AndroidX
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)

        // Compose BOM (ensures consistent Compose versions)
        implementation(platform(libs.androidx.compose.bom))

        // Compose UI & tooling
        implementation(libs.androidx.ui)
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.firebase.common.ktx)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)
        implementation(libs.ui.tooling.preview)

        // Material libraries
        implementation(libs.androidx.material3)
        implementation(libs.androidx.material)

        // *** MISSING DEPENDENCY FOR ICONS ***
        implementation(libs.androidx.material.icons.extended)

        // Foundation
        implementation(libs.androidx.foundation)

        // Testing
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)
        implementation(libs.hilt.android.v248)
        implementation(libs.kotlinx.serialization.json)
        kapt(libs.hilt.compiler.v248)
    }