package com.google.shinyay.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "keycloak.server")
class KeycloakServerProperties {
    private var contextPath: String? = "/auth"
    private var realmImportFile: String? = "keycloak-realm.json"
    private var adminUser: AdminUser? = AdminUser()
    fun getContextPath(): String? {
        return contextPath
    }

    fun setContextPath(contextPath: String?) {
        this.contextPath = contextPath
    }

    fun getAdminUser(): AdminUser? {
        return adminUser
    }

    fun setAdminUser(adminUser: AdminUser?) {
        this.adminUser = adminUser
    }

    fun getRealmImportFile(): String? {
        return realmImportFile
    }

    fun setRealmImportFile(realmImportFile: String?) {
        this.realmImportFile = realmImportFile
    }

    class AdminUser {
        private var username: String? = "admin"
        private var password: String? = "admin"
        fun getUsername(): String? {
            return username
        }

        fun setUsername(username: String?) {
            this.username = username
        }

        fun getPassword(): String? {
            return password
        }

        fun setPassword(password: String?) {
            this.password = password
        }
    }
}