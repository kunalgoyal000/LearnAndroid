package com.kunal.learnandroid.core.di

import com.kunal.learnandroid.daggerHilt.domain.repository.CatRepository
import com.kunal.learnandroid.daggerHilt.domain.repository.CatRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsCatRepository(
        catRepositoryImpl: CatRepositoryImpl
    ): CatRepository
}