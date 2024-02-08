plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ua.nure.chumchase"
    compileSdk = 34

    defaultConfig {
        applicationId = "ua.nure.chumchase"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "STREAM_API_KEY", "\"4pzprh9b3bsx\"")
        buildConfigField("String", "BASE_URL", "\"https://chumchase.onrender.com/api/\"")
        vectorDrawables {
            useSupportLibrary = true
        }

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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // compose
    val composePlatform = platform("androidx.compose:compose-bom:2023.10.01")
    implementation(composePlatform)
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.0")

    // navigation
    val navigation = "2.7.6"
    implementation("androidx.navigation:navigation-runtime-ktx:$navigation")
    implementation("androidx.navigation:navigation-compose:$navigation")

    // material
    val material = "1.5.4" // don't upgrade to 1.6.0 due to progress indicator crash
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material:$material")
    implementation("androidx.compose.material:material-icons-extended-android:$material")

    // koin
    val koin = "3.5.3"
    implementation("io.insert-koin:koin-android:$koin")
    implementation("io.insert-koin:koin-core:$koin")
    implementation("io.insert-koin:koin-androidx-compose:$koin")

    // timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // retrofit
    val retrofit = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // landscapist (glide, coil, fresco)
    implementation("com.github.skydoves:landscapist-glide:2.2.13")

    // stream chat
    val streamChat = "6.0.13"
    implementation("io.getstream:stream-chat-android-offline:$streamChat")
    implementation("io.getstream:stream-chat-android-compose:$streamChat")

    // test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(composePlatform)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}