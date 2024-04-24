package com.timetonic.displaybooksapp.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.timetonic.displaybooksapp.data.local.model.ApiKeyPreferencesDto
import com.timetonic.displaybooksapp.data.local.model.CredentialsDto
import com.timetonic.displaybooksapp.data.local.model.SessKeyPreferencesDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStoreManagementImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreManagement {
    override suspend fun saveApiKey(apiKeyPreferencesDto: ApiKeyPreferencesDto) {
        try {
            val response = dataStore.edit { preferences ->
                preferences[stringPreferencesKey("apiKey")] = apiKeyPreferencesDto.apiKey
            }
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getApiKey() = dataStore.data.map {preferences ->
        ApiKeyPreferencesDto(
            apiKey = preferences[stringPreferencesKey("apiKey")] ?: ""
        )
    }

    override suspend fun saveSessKey(sessKeyPreferencesDto: SessKeyPreferencesDto) {
        try {
            val response = dataStore.edit { preferences ->
                preferences[stringPreferencesKey("sessKey")] = sessKeyPreferencesDto.sessKey
            }
            Result.success(response)
        }catch (e:Exception){
            Result.failure(e)
        }
    }

    override suspend fun getSessKey(): Flow<SessKeyPreferencesDto> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCredentials(credentialsDto: CredentialsDto) {
        try {
            val response = dataStore.edit { preferences ->
                preferences[stringPreferencesKey("user")] = credentialsDto.user
                preferences[stringPreferencesKey("password")] = credentialsDto.password
            }
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCredentials(): Flow<CredentialsDto> = dataStore.data.map {preferences ->
        CredentialsDto(
            user = preferences[stringPreferencesKey("user")]  ?: "",
            password = preferences[stringPreferencesKey("password")] ?: ""
        )
    }
}