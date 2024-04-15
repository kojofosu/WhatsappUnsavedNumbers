import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
    id  ("com.android.application")
    id ("kotlin-android")
}

android {
    namespace = "com.mcdev.wun"
    defaultConfig {
        minSdk = 23
        targetSdk =34
        compileSdk  =34
        versionCode = 2
        versionName = "1.1"
        archivesName = "WUN-v${versionName}"

        testInstrumentationRunner  = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile ("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    val lottieVersion = "6.4.0"
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    //country code picker library
    implementation ("com.hbb20:ccp:2.7.0")
    //Lottie
    implementation ("com.airbnb.android:lottie:${lottieVersion}")
    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.20")
}
repositories {
    mavenCentral()
}