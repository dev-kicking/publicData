package dev.kick.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.kick.domain.repo.LocalCaloriesForAgeRepo
import dev.kick.domain.useCase.FindCaloriesForAgeListUseCase
import dev.kick.domain.useCase.GetCaloriesForAgeUseCase
import dev.kick.domain.useCase.GetLocalCaloriesForAgeListUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalCaloriesForAgeUseCaseModule {
    @Provides
    @Singleton
    fun provideGetLocalCaloriesForAgeListUseCase(
        repo: LocalCaloriesForAgeRepo,
    ): GetLocalCaloriesForAgeListUseCase = GetLocalCaloriesForAgeListUseCase(repo)

    @Provides
    @Singleton
    fun provideFindCaloriesForAgeListUseCase(
        repo: LocalCaloriesForAgeRepo,
    ): FindCaloriesForAgeListUseCase = FindCaloriesForAgeListUseCase(repo)

    @Provides
    @Singleton
    fun provideGetCaloriesForAgeUseCase(
        repo: LocalCaloriesForAgeRepo,
    ): GetCaloriesForAgeUseCase = GetCaloriesForAgeUseCase(repo)
}