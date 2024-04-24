package com.timetonic.displaybooksapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.timetonic.displaybooksapp.data.local.DataStoreManagement
import com.timetonic.displaybooksapp.data.local.DataStoreManagementImpl
import com.timetonic.displaybooksapp.utils.datastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalAppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.datastore
    }

    @Provides
    @Singleton
    fun provideDataStorePreference(dataStore: DataStore<Preferences>): DataStoreManagement{
        return DataStoreManagementImpl(dataStore)
    }
}