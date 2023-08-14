plugins {
    id("org.sonarqube")
}

sonar {
    properties {
        property("sonar.projectKey", "digitalservicebund_${rootProject.name}")
        property("sonar.organization", "digitalservicebund")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}
