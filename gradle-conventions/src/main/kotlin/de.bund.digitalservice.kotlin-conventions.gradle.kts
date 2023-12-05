plugins {
    org.jetbrains.kotlin.jvm
}

java.sourceCompatibility = JavaVersion.VERSION_17

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}
