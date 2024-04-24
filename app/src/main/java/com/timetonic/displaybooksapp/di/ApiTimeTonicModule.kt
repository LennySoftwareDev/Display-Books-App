package com.timetonic.displaybooksapp.di

import com.timetonic.displaybooksapp.data.api.ApiTimeTonicService
import com.timetonic.displaybooksapp.utils.ConstantValues
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiTimeTonicModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ConstantValues.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCreateApiKeyService(retrofit: Retrofit): ApiTimeTonicService {
        return retrofit.create(ApiTimeTonicService::class.java)
    }
}