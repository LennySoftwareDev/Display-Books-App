package com.timetonic.displaybooksapp.data.local.repository

import com.timetonic.displaybooksapp.data.local.model.ApiKeyPreferencesDto
import com.timetonic.displaybooksapp.data.local.model.CredentialsDto
import com.timetonic.displaybooksapp.data.local.model.SessKeyPreferencesDto
import com.timetonic.displaybooksapp.data.local.DataStoreManagement
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStore: DataStoreManagement
) {
    suspend fun saveApiKeyDataStore(apiKeyPreferencesDto: ApiKeyPreferencesDto) =
        dataStore.saveApiKey(apiKeyPreferencesDto)

    suspend fun getApiKeyDataStore() =
        dataStore.getApiKey()

    suspend fun saveCredentialsDataStore(credentialsDto: CredentialsDto) =
        dataStore.saveCredentials(credentialsDto)

    suspend fun getCredentialsDataStore() =
        dataStore.getCredentials()

    suspend fun saveSessKeyDataStore(sessKeyPreferencesDto: SessKeyPreferencesDto) =
        dataStore.saveSessKey(sessKeyPreferencesDto)
}