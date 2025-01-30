plugins {
    java
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    id("io.freefair.lombok") version "8.6"
}

group = "ru.kaledin170317"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("org.springframework.boot:spring-boot-maven-plugin:3.3.0")
    testImplementation("org.testcontainers:kafka:1.19.7")
    testImplementation("org.springframework.kafka:spring-kafka-test:3.2.0")

    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.apache.kafka:kafka-clients:3.7.0")

    implementation("com.fasterxml.jackson.core:jackson-core:2.17.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")

    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation ("org.hibernate:hibernate-core:6.4.4.Final")
    runtimeOnly("org.postgresql:postgresql")

    implementation ("com.auth0:java-jwt:4.4.0")
    implementation("org.springframework.security:spring-security-web:6.2.0")
    implementation("org.springframework.security:spring-security-config:6.2.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.5")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.2.1")
    implementation ("org.springframework.boot:spring-boot-starter-security:3.2.4")
    implementation("org.springframework.security:spring-security-core:6.2.0")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0-M1")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")
    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-impl")
    implementation("io.jsonwebtoken:jjwt:0.12.3")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
