val exposedVersion: String by project
val junitVersion: String by project
val hikariVersion: String by project
val h2Version: String by project

plugins {
    kotlin("jvm") version "1.9.22"
}

group = "com.strongmandrew"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")

    testImplementation("com.h2database:h2:$h2Version")

    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
        outputs.upToDateWhen { false }
    }
}

kotlin {
    jvmToolchain(17)
}