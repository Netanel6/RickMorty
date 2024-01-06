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

    // Provides a singleton instance of Retrofit using RetrofitInstance.
    @Singleton
    @Provides
    fun provideRetrofitInstance() = RetrofitInstance

    // Provides a singleton instance of ApiService using RetrofitInstance.
    // The ApiService instance is created based on the ApiService interface.
    @Singleton
    @Provides
    fun provideNetworkManager(retrofitInstance: RetrofitInstance): ApiService {
        return retrofitInstance.create(ApiService::class.java)
    }
}
