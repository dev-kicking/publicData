package dev.kick.public_data.di

import dev.kick.domain.repo.AppApplication
import dev.kick.public_data.BuildConfig

class AppApplicationInfo: AppApplication {
    override fun getDebug() = BuildConfig.DEBUG
    override fun getOpenApiKey() = BuildConfig.SERVICE_KEY
}