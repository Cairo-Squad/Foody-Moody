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
        csv.required.set(false)
        xml.required.set(true)
        html.required.set(true)
    }
    dependsOn(tasks.test)
}

jacoco {
    toolVersion = "0.8.10"
}


tasks.jacocoTestCoverageVerification {
    executionData.setFrom(fileTree(buildDir).include("/jacoco/test.exec"))
    classDirectories.setFrom(fileTree("build/classes/kotlin/main"))
    sourceDirectories.setFrom(files("src/main/kotlin"))
    violationRules {
        rule {
            limit {
                counter = "CLASS"
                value = "COVEREDRATIO"
                minimum = "1.0".toBigDecimal() // 100% coverage

            }
        }
    }
}

kotlin {
    jvmToolchain(22)
}