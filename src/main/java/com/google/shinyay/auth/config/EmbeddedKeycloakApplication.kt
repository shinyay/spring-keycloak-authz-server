package com.google.shinyay.auth.config

import com.google.shinyay.auth.logger
import org.keycloak.Config
import org.keycloak.representations.idm.RealmRepresentation
import org.keycloak.services.managers.ApplianceBootstrap
import org.keycloak.services.managers.RealmManager
import org.keycloak.services.resources.KeycloakApplication
import org.keycloak.services.util.JsonConfigProviderFactory
import org.keycloak.util.JsonSerialization
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource

class EmbeddedKeycloakApplication : KeycloakApplication() {
    override fun loadConfig() {
        val factory: JsonConfigProviderFactory = RegularJsonConfigProviderFactory()
        Config.init(factory.create()
            .orElseThrow { NoSuchElementException("No value present") })
    }

    private fun createMasterRealmAdminUser() {
        val session = getSessionFactory().create()
        val applianceBootstrap = ApplianceBootstrap(session)
        val admin = keycloakServerProperties?.getAdminUser()
        try {
            session.transactionManager.begin()
            applianceBootstrap.createMasterRealmUser(admin?.getUsername(), admin?.getPassword())
            session.transactionManager.commit()
        } catch (ex: Exception) {
            logger.warn("Couldn't create keycloak master admin user: {}", ex.message)
            session.transactionManager.rollback()
        }
        session.close()
    }

    private fun createKeycloakRealm() {
        val session = getSessionFactory().create()
        try {
            session.transactionManager.begin()
            val manager = RealmManager(session)
            val lessonRealmImportFile: Resource = ClassPathResource(keycloakServerProperties?.getRealmImportFile()!!)
            manager.importRealm(
                JsonSerialization.readValue(lessonRealmImportFile.inputStream, RealmRepresentation::class.java)
            )
            session.transactionManager.commit()
        } catch (ex: Exception) {
            logger.warn("Failed to import Realm json file: {}", ex.message)
            session.transactionManager.rollback()
        }
        session.close()
    }

    companion object {
        var keycloakServerProperties: KeycloakServerProperties? = null
    }

    init {
        createMasterRealmAdminUser()
        createKeycloakRealm()
    }
}