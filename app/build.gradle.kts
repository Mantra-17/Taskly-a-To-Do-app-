plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.taskly"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.taskly"
        minSdk = 24
        targetSdk = 34
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

    // ✅ Enable ViewBinding for cleaner UI code
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // AndroidX + Material
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Additional libs needed for our login/signup UI
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.viewpager2:viewpager2:1.1.0")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
