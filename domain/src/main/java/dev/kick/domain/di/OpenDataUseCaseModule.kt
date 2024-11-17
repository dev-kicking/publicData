package dev.kick.domain.di

import dev.kick.domain.repo.OpenDataRepo
import dev.kick.domain.useCase.GetCaloriesForAgeListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.kick.domain.useCase.FindCaloriesForAgeListUseCase
import dev.kick.domain.useCase.GetLocalCaloriesForAgeListUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OpenDataUseCaseModule {
    @Provides
    @Singleton
    fun provideGetCaloriesForAgeListUseCase(
        repo: OpenDataRepo,
    ): GetCaloriesForAgeListUseCase = GetCaloriesForAgeListUseCase(repo)

    @Provides
    @Singleton
    fun provideGetLocalCaloriesForAgeListUseCase(
        repo: OpenDataRepo,
    ): GetLocalCaloriesForAgeListUseCase = GetLocalCaloriesForAgeListUseCase(repo)

    @Provides
    @Singleton
    fun provideFindCaloriesForAgeListUseCase(
        repo: OpenDataRepo,
    ): FindCaloriesForAgeListUseCase = FindCaloriesForAgeListUseCase(repo)
}