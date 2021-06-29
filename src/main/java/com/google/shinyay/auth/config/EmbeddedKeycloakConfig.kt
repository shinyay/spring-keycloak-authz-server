package com.google.shinyay.auth.config

import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher
import org.jboss.resteasy.plugins.server.servlet.ResteasyContextParameters
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*
import javax.naming.*
import javax.naming.spi.InitialContextFactory
import javax.naming.spi.NamingManager
import javax.sql.DataSource

@Configuration
class EmbeddedKeycloakConfig {
    @Bean
    @Throws(Exception::class)
    fun keycloakJaxRsApplication(
        keycloakServerProperties: KeycloakServerProperties?, dataSource: DataSource?
    ): ServletRegistrationBean<HttpServlet30Dispatcher?>? {
        mockJndiEnvironment(dataSource)
        EmbeddedKeycloakApplication.keycloakServerProperties = keycloakServerProperties
        val servlet = ServletRegistrationBean(
            HttpServlet30Dispatcher()
        )
        servlet.addInitParameter("javax.ws.rs.Application", EmbeddedKeycloakApplication::class.java.name)
        servlet.addInitParameter(
            ResteasyContextParameters.RESTEASY_SERVLET_MAPPING_PREFIX,
            keycloakServerProperties?.getContextPath()
        )
        servlet.addInitParameter(ResteasyContextParameters.RESTEASY_USE_CONTAINER_FORM_PARAMS, "true")
        servlet.addUrlMappings(keycloakServerProperties?.getContextPath() + "/*")
        servlet.setLoadOnStartup(1)
        servlet.isAsyncSupported = true
        return servlet
    }

    @Bean
    fun keycloakSessionManagement(keycloakServerProperties: KeycloakServerProperties?): FilterRegistrationBean<EmbeddedKeycloakRequestFilter?>? {
        val filter = FilterRegistrationBean<EmbeddedKeycloakRequestFilter?>()
        filter.setName("Keycloak Session Management")
        filter.filter = EmbeddedKeycloakRequestFilter()
        filter.addUrlPatterns(keycloakServerProperties?.getContextPath() + "/*")
        return filter
    }

    @Throws(NamingException::class)
    private fun mockJndiEnvironment(dataSource: DataSource?) {
        NamingManager.setInitialContextFactoryBuilder {
            InitialContextFactory {
                object : InitialContext() {
                    override fun lookup(name: Name?): Any? {
                        return lookup(name.toString())
                    }

                    override fun lookup(name: String?): Any? {
                        if ("spring/datasource" == name) {
                            return dataSource
                        }
                        return null
                    }

                    override fun getNameParser(name: String?): NameParser {
                        return NameParser { n: String? -> CompositeName(n) }
                    }

                    override fun close() {
                        // NOOP
                    }
                }
            }
        }
    }
}