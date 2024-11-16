package dev.kick.domain.di

import dev.kick.domain.repo.OpenDataRepo
import dev.kick.domain.useCase.GetCaloriesForAgeListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OpenDataUseCaseModule {
    @Provides
    @Singleton
    fun provideGetCaloriesForAgeListUseCase(
        repo: OpenDataRepo
    ): GetCaloriesForAgeListUseCase = GetCaloriesForAgeListUseCase(repo)
}