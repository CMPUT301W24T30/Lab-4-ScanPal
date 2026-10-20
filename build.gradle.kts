buildscript {
    dependencies {
        add("classpath", platform("io.netty:netty-bom:4.1.137.Final"))
        constraints {
            add("classpath", "com.google.guava:guava:32.0.1-jre") {
                because("CVE-2023-2976 fixes insecure temporary-file permissions")
            }
            add("classpath", "com.google.protobuf:protobuf-java:3.25.8") {
                because("Includes the Protobuf 3.x parser denial-of-service fixes")
            }
            add("classpath", "commons-io:commons-io:2.14.0") {
                because("CVE-2024-47554 fixes unbounded XML input processing")
            }
            add("classpath", "org.apache.commons:commons-compress:1.26.0") {
                because("CVE-2024-25710 and CVE-2024-26308 fix archive parser denial of service")
            }
            add("classpath", "org.apache.commons:commons-lang3:3.18.0") {
                because("CVE-2025-48924 fixes recursive class-name parsing")
            }
            add("classpath", "org.bitbucket.b_c:jose4j:0.9.6") {
                because("Includes JWT/JWE parser and denial-of-service fixes")
            }
            add("classpath", "org.jdom:jdom2:2.0.6.1") {
                because("CVE-2021-33813 fixes XML external entity injection")
            }
            add("classpath", "xerces:xercesImpl:2.12.2") {
                because("Includes XML parser security fixes")
            }
        }
    }
    configurations.configureEach {
        resolutionStrategy.dependencySubstitution {
            substitute(module("org.bouncycastle:bcpkix-jdk15on"))
                .using(module("org.bouncycastle:bcpkix-jdk18on:1.85"))
                .because("The maintained Java 8+ artifacts contain current security fixes")
            substitute(module("org.bouncycastle:bcprov-jdk15on"))
                .using(module("org.bouncycastle:bcprov-jdk18on:1.85"))
                .because("CVE-2026-8763 and CVE-2026-13506 require Bouncy Castle 1.85")
        }
    }
}

plugins {
    id("com.android.application") version "8.1.1" apply false
}
