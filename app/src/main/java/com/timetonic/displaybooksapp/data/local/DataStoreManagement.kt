package com.timetonic.displaybooksapp.data.local

import com.timetonic.displaybooksapp.data.local.model.ApiKeyPreferencesDto
import com.timetonic.displaybooksapp.data.local.model.CredentialsDto
import com.timetonic.displaybooksapp.data.local.model.SessKeyPreferencesDto
import kotlinx.coroutines.flow.Flow

interface DataStoreManagement {
    suspend fun saveApiKey(apiKeyPreferencesDto: ApiKeyPreferencesDto)
    suspend fun getApiKey(): Flow<ApiKeyPreferencesDto>
    suspend fun saveSessKey(sessKeyPreferencesDto: SessKeyPreferencesDto)
    suspend fun getSessKey(): Flow<SessKeyPreferencesDto>
    suspend fun saveCredentials(credentialsDto: CredentialsDto)
    suspend fun getCredentials(): Flow<CredentialsDto>

}