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
    testImplementation ("io.rest-assured:rest-assured:5.5.0")
    implementation ("org.json:json:20230227")
}

tasks.test {
    useJUnitPlatform()
}