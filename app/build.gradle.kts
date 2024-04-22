@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.googleServices)
    id("com.google.firebase.crashlytics")
    id("kotlin-parcelize")
    id("com.apollographql.apollo3").version("3.8.3")
}

android {
    namespace = "com.kunal.learnandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kunal.learnandroid"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    sourceSets {
        // Adds exported schema location as test app assets.
        getByName("androidTest").assets.srcDir("$projectDir/schemas")
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    //BIOMETRIC
    implementation(libs.biometric)

    //DOCUMENT SCANNER
    implementation(libs.play.services.mlkit.document.scanner)

    //COIL IMAGE LOADER
    implementation(libs.coil.compose)

    //SPLASH SCREEN
    implementation(libs.androidx.core.splashscreen)

    //LIFECYCLE VIEWMODEL COMPOSE
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //DAGGER-HILT
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    //NETWORKING LIBRARY
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.moshi)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    //CAMERAX
    implementation(libs.bundles.cameraXDependencies)
    implementation(libs.androidx.material.icons.extended)

    //TENSOR FLOW LITE
    implementation(libs.bundles.tensorFlowLiteDependencies)

    //PLAY SERVICES
    implementation(libs.play.services.auth)
    implementation(libs.play.services.location)

    //FIREBASE
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)

    //ROOM DB
//    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    //ROOM DB TESTING
    androidTestImplementation(libs.androidx.room.testing)
    androidTestImplementation(libs.truth)

    //PAGING
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.room.paging)

    //GLANCE FOR WIDGETS
    implementation(libs.androidx.glance)
    implementation(libs.androidx.glance.appwidget)

    //EXOPLAYER
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.exoplayer.dash)
    implementation(libs.androidx.media3.ui)

    //WINDOW SIZE CLASS MATERIAL3
    implementation(libs.androidx.material3.window.size)

    //APOLLO GRAPHQL
    implementation(libs.apollo.runtime)

}

apollo {
    service("service") {
        packageName.set("com.kunal")
    }
}