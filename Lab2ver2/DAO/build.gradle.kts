plugins {
    id("java")
    id("io.freefair.lombok").version("8.6")
    id("org.hibernate.orm").version("6.4.4.Final")
}

group = "ru.kaledin170317"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly(group = "org.postgresql", name = "postgresql", version = "42.1.4")
}




tasks.test {
    useJUnitPlatform()
}
