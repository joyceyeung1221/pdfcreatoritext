import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

group = "com.myob.bankfeeds"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.apache.pdfbox:pdfbox:2.0.25")
    implementation("com.itextpdf:itextpdf:5.5.13.2")
    implementation("com.google.zxing:core:3.3.0")
    implementation("com.google.zxing:javase:3.3.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("com.github.librepdf:openpdf:1.3.26")
    implementation("net.sf.barcode4j:barcode4j:2.1")
    implementation("net.sourceforge.jchardet:jchardet:1.0")
    implementation("com.googlecode.juniversalchardet:juniversalchardet:1.0.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
