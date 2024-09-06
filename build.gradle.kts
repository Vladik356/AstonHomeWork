plugins {
    id("java")
    id("io.qameta.allure") version("2.9.6")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
val allureVersion = "2.25.0"

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation ("io.github.bonigarcia:webdrivermanager:5.9.2")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.11.0")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.11.0")
    implementation ("org.seleniumhq.selenium:selenium-java:4.23.0")

    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-junit5")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy ("allureReport")
}

val aspectJVersion = "1.9.21"

val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}

dependencies {

    agent("org.aspectj:aspectjweaver:${aspectJVersion}")
}

tasks.test {
    jvmArgs = listOf(
        "-javaagent:${agent.singleFile}"
    )
}
