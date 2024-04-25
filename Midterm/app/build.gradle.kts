plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.midterm.letattuan"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.midterm.letattuan"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)


//    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
//    implementation ("com.google.code.gson:gson:2.10.1")
//    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2")
//    implementation ("io.reactivex.rxjava3:rxjava:3.1.5")
//    implementation ("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")
//    implementation("androidx.navigation:navigation-fragment:2.7.7")
//    implementation("androidx.navigation:navigation-ui:2.7.7")
//    implementation("com.squareup.picasso:picasso:2.71828")
//
//
//    implementation("com.google.firebase:firebase-auth:22.3.1")
//    implementation("com.google.firebase:firebase-database:20.3.1")
//    implementation("com.google.firebase:firebase-firestore:24.11.0")
//    implementation("com.firebaseui:firebase-ui-database:8.0.2")
//    implementation("com.firebaseui:firebase-ui-firestore:8.0.2")
//    implementation("com.firebaseui:firebase-ui-auth:8.0.2")
//    implementation("androidx.activity:activity:1.8.0")
////    implementation ("com.firebaseui:firebase-ui-storage:8.0.2")
//
//
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    implementation("androidx.room:room-rxjava2:$room_version")
    implementation("androidx.room:room-rxjava3:$room_version")
    implementation("androidx.room:room-guava:$room_version")
    testImplementation("androidx.room:room-testing:$room_version")
    implementation("androidx.room:room-paging:$room_version")


    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}