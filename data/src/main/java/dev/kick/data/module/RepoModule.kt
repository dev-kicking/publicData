package dev.kick.data.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.kick.data.datasource.AppDatabase
import dev.kick.data.repo.OpenDataRepoImpl
import dev.kick.data.service.OpenDataService
import dev.kick.domain.repo.AppApplication
import dev.kick.domain.repo.OpenDataRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    @Singleton
    fun provideOpenDataRepository(
        openDataService: OpenDataService,
        appApplication: AppApplication,
        appDatabase: AppDatabase,
    ): OpenDataRepo {
        return OpenDataRepoImpl(openDataService, appApplication, appDatabase)
    }
}