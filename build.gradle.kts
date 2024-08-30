plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation ("io.github.bonigarcia:webdrivermanager:5.3.2")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.11.0")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.11.0")
    implementation ("org.seleniumhq.selenium:selenium-java:4.11.0")
}

tasks.test {
    useJUnitPlatform()
}