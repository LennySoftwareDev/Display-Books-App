package com.timetonic.displaybooksapp.ui.view.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timetonic.displaybooksapp.utils.ConstantValues
import com.timetonic.displaybooksapp.data.api.modelresponse.CreateApiKeyResponseDto
import com.timetonic.displaybooksapp.data.api.repository.TimeTonicRepository
import com.timetonic.displaybooksapp.data.local.model.ApiKeyPreferencesDto
import com.timetonic.displaybooksapp.data.local.model.CredentialsDto
import com.timetonic.displaybooksapp.data.local.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val timeTonicRepository: TimeTonicRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _dataCreateApiKey = MutableLiveData<Result<CreateApiKeyResponseDto>>()

    private val _apiKey = MutableLiveData<String>()
    val apiKey: LiveData<String> = _apiKey

    private var apiKeyPreferencesDto: ApiKeyPreferencesDto = ApiKeyPreferencesDto(apiKey = "")

    private var apiKeyObtained = false

    fun saveCredentialByDefault() {
        val credentialsDto = CredentialsDto(
            user = ConstantValues.USER_BY_DEFAULT,
            password = ConstantValues.PASSWORD_BY_DEFAULT
        )
        viewModelScope.launch {
            credentialsDto.let { dataStoreRepository.saveCredentialsDataStore(it) }
        }
    }

    private fun getApiKeyTimeTonic() {

        viewModelScope.launch {
            _dataCreateApiKey.value = timeTonicRepository.getApiKeyFromApi()
            _apiKey.value = _dataCreateApiKey.value!!.getOrNull()?.appKey.toString()
        }
    }

    private fun saveApiKeyDataStore(apiKeyPreferencesDto: ApiKeyPreferencesDto) {

        this.apiKeyPreferencesDto = ApiKeyPreferencesDto(
            apiKey = _apiKey.value.toString()
        )

        viewModelScope.launch {
            apiKeyPreferencesDto.let { dataStoreRepository.saveApiKeyDataStore(it) }
        }
    }

    fun selectApiKey() {
        viewModelScope.launch {

            val getApiKeyDataStore = getApiKeyDataStore()

            if (getApiKeyDataStore.isNotEmpty()) {
                _apiKey.value = getApiKeyDataStore
            } else {
                getApiKeyTimeTonic()
                saveApiKeyDataStore(apiKeyPreferencesDto)
            }
        }

    }

    private suspend fun getApiKeyDataStore(): String {

        if (!apiKeyObtained) {
            val apiKey = dataStoreRepository.getApiKeyDataStore()
                .first().apiKey

            apiKeyObtained = true
            return apiKey
        }
        return ""
    }
}