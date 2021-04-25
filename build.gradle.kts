plugins {
    id 'groovy'
    id 'java-gradle-plugin'
    id 'com.gradle.plugin-publish' version '0.14.0'
}

repositories {
    mavenCentral()
}

ext {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

pluginBundle {
    website = 'https://github.com/jsonschema2dataclass/js2d-gradle'
    vcsUrl = 'https://github.com/jsonschema2dataclass/js2d-gradle.git'
    tags = ['json-schema', 'jsonschema', 'generator', 'pojo', 'jsonschema2pojo', 'dataclass', 'data', 'json', 'generation', 'jsonschema2dataclass', 'java']
}


gradlePlugin {
    plugins {
        jsonschema2dataclassPlugin {
            id = 'org.jsonschema2dataclass'
            version = getTag()
            implementationClass = 'com.github.js2d.JsonSchemaPlugin'
            displayName = 'Extended Gradle JsonSchema2Pojo Plugin'
            description = 'A plugins that generates Java sources from JsonSchema using jsonschema2pojo. Please, see the GitHub page for details'
        }
    }
}

dependencies {
    implementation 'org.codehaus.groovy:groovy-all:3.0.8'
    implementation 'org.jsonschema2pojo:jsonschema2pojo-core:1.1.1'

    testImplementation platform('org.junit:junit-bom:5.7.1')

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation gradleTestKit()
}

test {
    useJUnitPlatform()
}

static def getTag() {
    def tagVersion = "$System.env.VERSION"
    if (tagVersion == "null") {
        // with local un-commited changes a -DIRTY is added
        def processChanges = "git diff-index --name-only HEAD --".execute()
        def dirty = ""
        if (!processChanges.text.toString().trim().isEmpty())
            dirty = "-DIRTY"

        def process = "git describe --tags".execute()
        tagVersion = process.text.toString().trim() + dirty
    } else {
        def tagVersionToken = tagVersion.split("/")
        if (tagVersionToken.size() > 2)
            tagVersion = tagVersionToken[2]
        else
            tagVersion = tagVersionToken[0]
    }
    return tagVersion
}
