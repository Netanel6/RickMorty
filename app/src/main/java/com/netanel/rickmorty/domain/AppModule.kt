package com.netanel.rickmorty.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by netanelamar on 05/01/2024.
 * NetanelCA2@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance() = RetrofitInstance

    @Singleton
    @Provides
    fun provideNetworkManager(retrofitInstance: RetrofitInstance): ApiService {
        return retrofitInstance.create(ApiService::class.java)
    }
}
