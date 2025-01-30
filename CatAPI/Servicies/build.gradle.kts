plugins {
    id("java")
    id("org.springframework.boot") version "3.2.5" apply false
    id("io.spring.dependency-management") version "1.1.4"
    id("io.freefair.lombok") version "8.6"
}

group = "ru.kaledin170317"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.5")
    implementation ("com.auth0:java-jwt:4.4.0")
    implementation("org.springframework.security:spring-security-web:6.2.0")
    implementation("org.springframework.security:spring-security-config:6.2.0")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.2.1")
    implementation ("org.springframework.boot:spring-boot-starter-security:3.2.4")
    implementation("org.springframework.security:spring-security-core:6.2.0")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0-M1")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")
    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
    implementation("io.jsonwebtoken:jjwt:0.12.3")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")


    implementation(project(":CatAPI:Repository"))
    implementation(project(":CatAPI:Entities"))
}

tasks.test {
    useJUnitPlatform()
}