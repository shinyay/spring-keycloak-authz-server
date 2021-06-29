package com.google.shinyay.auth

import com.google.shinyay.auth.config.KeycloakServerProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean

@SpringBootApplication(exclude = [LiquibaseAutoConfiguration::class])
@EnableConfigurationProperties(
    KeycloakServerProperties::class
)
class AuthorizationServerApp {
    private val LOG = LoggerFactory.getLogger(AuthorizationServerApp::class.java)

    @Bean
    fun onApplicationReadyEventListener(
        serverProperties: ServerProperties?,
        keycloakServerProperties: KeycloakServerProperties?
    ): ApplicationListener<ApplicationReadyEvent?>? {
        return ApplicationListener {
            val port = serverProperties?.port
            val keycloakContextPath = keycloakServerProperties?.getContextPath()
            LOG.info("Embedded Keycloak started: http://localhost:{}{} to use keycloak", port, keycloakContextPath)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<AuthorizationServerApp>(*args)
}

val Any.logger: Logger
    get() = LoggerFactory.getLogger(this::class.java)