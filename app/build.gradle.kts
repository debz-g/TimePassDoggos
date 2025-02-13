plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.serialization)
    alias(libs.plugins.androidx.navigation.safeargs)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "dev.redfox.timepassdoggo"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.redfox.timepassdoggo"
        minSdk = 26
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
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    /**Third party libs**/

    // Retrofit
    implementation(libs.retrofit)
    //Gson Converter
    implementation(libs.gson)
    implementation(libs.gson.convertor)
    //Paging3
    implementation(libs.androidx.paging.runtime.ktx)
    //Glide
    implementation(libs.glide)
    //Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    //OkHttp3
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    // Lifecycles only (without ViewModel or LiveData)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    //navigation component
    // Views/Fragments integration
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    // Feature module support for Fragments
    implementation(libs.androidx.navigation.dynamic.features.fragment)
    // JSON serialization library, works with the Kotlin serialization plugin
    implementation(libs.kotlinx.serialization.json)
    //Shimmer
    implementation(libs.shimmer)
    //Room DB
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}

buildscript {
    dependencies {
        classpath(libs.gradle)
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
    }
}