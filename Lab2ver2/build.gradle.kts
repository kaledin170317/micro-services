
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
    runtimeOnly(group = "org.dbunit", name = "dbunit", version = "2.5.1")

    implementation(project("DAO"))
    implementation(project("Service"))
    implementation(project("Controller"))
    testImplementation("com.h2database:h2:2.1.214")

}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "ru.kaledin170317.lab2ver2.Main"
    }
}


tasks.test {
    useJUnitPlatform()
}


tasks.create<JavaExec>("vER2") {
    mainClass.set("ru.kaledin170317.lab2ver2.Main")
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.withType<Javadoc> {
    (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
}