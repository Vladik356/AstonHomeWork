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
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.11.0")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.11.0")
    testImplementation ("org.testng:testng:7.10.2")
    testImplementation ("ch.qos.logback:logback-classic:1.2.11")
}

tasks.test {
    useJUnitPlatform()
    useTestNG()
}