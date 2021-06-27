package com.google.shinyay.auth

import com.google.shinyay.auth.config.KeycloakServerProperties
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean

@SpringBootApplication(exclude = [LiquibaseAutoConfiguration::class])
@EnableConfigurationProperties(
    KeycloakServerProperties::class
)
open class AuthorizationServerApp {
    @Bean
    open fun onApplicationReadyEventListener(
        serverProperties: ServerProperties?,
        keycloakServerProperties: KeycloakServerProperties?
    ): ApplicationListener<ApplicationReadyEvent?>? {
        return ApplicationListener { evt: ApplicationReadyEvent? ->
            val port = serverProperties?.getPort()
            val keycloakContextPath = keycloakServerProperties?.getContextPath()
            LOG.info("Embedded Keycloak started: http://localhost:{}{} to use keycloak", port, keycloakContextPath)
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(AuthorizationServerApp::class.java)
        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(AuthorizationServerApp::class.java, *args)
        }
    }
}