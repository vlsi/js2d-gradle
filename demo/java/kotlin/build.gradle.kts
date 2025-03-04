plugins {
    kotlin("jvm") version "1.7.10"
    id("org.jsonschema2dataclass") version "4.2.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")

    // see src/main/resources/json/external_dependencies.json
    implementation("joda-time:joda-time:2.11.0")
}

jsonSchema2Pojo {
    targetPackage.set("example")
    propertyWordDelimiters.set("_")
    includeGeneratedAnnotation.set(false)
}
