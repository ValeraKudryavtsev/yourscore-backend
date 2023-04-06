import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.6.0"
}

group = "com.project"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	// Security
	implementation("org.springframework.boot:spring-boot-starter-security")
	// Password Encoder
	implementation("org.springframework.security:spring-security-crypto")
	// Tests
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	// MySQL Support
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("mysql:mysql-connector-java")
	// Email dependencies
	implementation("org.springframework.boot:spring-boot-starter-mail")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
