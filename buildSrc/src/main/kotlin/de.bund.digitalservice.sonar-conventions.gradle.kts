plugins {
    id("org.sonarqube")
}

sonar {
    properties {
        property("sonar.projectKey", "digitalservicebund_kotlin-application-template")
        property("sonar.organization", "digitalservicebund")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}
