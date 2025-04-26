plugins {
    kotlin("jvm") version "2.1.10"
    jacoco
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.insert-koin:koin-core:4.0.3")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.12.2")
    testImplementation("com.google.truth:truth:1.4.4")
    testImplementation("io.kotest:kotest-assertions-core:5.9.0")
    testImplementation("io.mockk:mockk:1.14.0")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    dependsOn(tasks.test)
}

tasks.jacocoTestCoverageVerification {
    executionData.setFrom(fileTree(layout.buildDirectory).include("/jacoco/test.exec"))
    classDirectories.setFrom(fileTree("build/classes/kotlin/main/logic/usecase"))
    sourceDirectories.setFrom(files("src/main/kotlin/logic/usecase"))
    violationRules {
        rule {
            limit {
                counter = "CLASS"
                value = "COVEREDRATIO"
                minimum = "1.0".toBigDecimal()

            }
        }
    }
}

kotlin {
    jvmToolchain(22)
}