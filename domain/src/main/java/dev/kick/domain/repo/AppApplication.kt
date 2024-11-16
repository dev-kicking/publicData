package dev.kick.domain.repo

interface AppApplication {
    fun getDebug(): Boolean
    fun getOpenApiKey(): String
}