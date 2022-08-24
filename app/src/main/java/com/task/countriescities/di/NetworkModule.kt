package com.task.countriescities.di

import com.task.countriescities.api.CountriesnowService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideCountriesnowService(): CountriesnowService {
        return CountriesnowService.create()
    }
}