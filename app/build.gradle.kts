plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.effectivemobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.effectivemobile"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation (libs.material)

    //Dagger
    implementation(libs.dagger)
    kapt (libs.dagger.compiler)
    implementation(libs.javax.inject)

    //AdapterDelegates
    implementation (libs.adapterdelegates4.kotlin.dsl)

    //Gson
    implementation (libs.converter.gson)
    implementation (libs.gson)

    //RxJava2
    implementation (libs.adapter.rxjava2)
    implementation (libs.rxandroid)
    implementation (libs.rxjava)

    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)

    //Room
    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)

    //Domain Module
    implementation(project(":domain"))

    //Data Module
    implementation(project(":data"))


}