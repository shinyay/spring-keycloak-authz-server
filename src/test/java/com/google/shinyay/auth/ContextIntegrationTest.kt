package com.google.shinyay.auth

import org.keycloak.common.util.ResteasyProvider
import org.jboss.resteasy.spi.ResteasyProviderFactory
import kotlin.Throws
import com.google.shinyay.auth.config.KeycloakServerProperties
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher
import com.google.shinyay.auth.config.EmbeddedKeycloakApplication
import org.jboss.resteasy.plugins.server.servlet.ResteasyContextParameters
import org.springframework.boot.web.servlet.FilterRegistrationBean
import com.google.shinyay.auth.config.EmbeddedKeycloakRequestFilter
import javax.naming.NamingException
import javax.naming.spi.NamingManager
import javax.naming.spi.InitialContextFactoryBuilder
import javax.naming.spi.InitialContextFactory
import javax.naming.InitialContext
import javax.naming.NameParser
import java.lang.Runnable
import org.keycloak.services.ServicesLogger
import com.google.shinyay.auth.config.KeycloakServerProperties.AdminUser
import org.keycloak.services.resources.KeycloakApplication
import org.keycloak.services.util.JsonConfigProviderFactory
import com.google.shinyay.auth.config.RegularJsonConfigProviderFactory
import java.util.NoSuchElementException
import org.keycloak.models.KeycloakSession
import org.keycloak.services.managers.ApplianceBootstrap
import org.keycloak.services.managers.RealmManager
import org.springframework.core.io.ClassPathResource
import org.keycloak.util.JsonSerialization
import org.keycloak.representations.idm.RealmRepresentation
import org.keycloak.services.filters.AbstractRequestFilter
import java.io.UnsupportedEncodingException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.FilterChain
import org.keycloak.common.ClientConnection
import javax.servlet.http.HttpServletRequest
import java.lang.RuntimeException
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.context.ApplicationListener
import org.springframework.boot.context.event.ApplicationReadyEvent
import com.google.shinyay.auth.AuthorizationServerApp
import org.junit.jupiter.api.Test
import kotlin.jvm.JvmStatic
import org.springframework.boot.SpringApplication
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.boot.test.context.SpringBootTest

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [AuthorizationServerApp::class])
class ContextIntegrationTest {
    @Test
    fun whenLoadApplication_thenSuccess() {
    }
}