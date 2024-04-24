package com.timetonic.displaybooksapp.ui.view.login

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timetonic.displaybooksapp.data.api.modelresponse.CreateOAuthKeyResponseDto
import com.timetonic.displaybooksapp.data.api.repository.TimeTonicRepository
import com.timetonic.displaybooksapp.data.local.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val timeTonicRepository: TimeTonicRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _dataCreateOAuthKey = MutableLiveData<Result<CreateOAuthKeyResponseDto>?>()
    var dataCreateOAuthKey: LiveData<Result<CreateOAuthKeyResponseDto>?> = _dataCreateOAuthKey

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _apiKey = MutableLiveData<String>()
    val apiKey: LiveData<String> = _apiKey

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private var _isLoginSuccess = MutableLiveData<Boolean>()
    val isLoginSuccess: LiveData<Boolean> = _isLoginSuccess

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    fun getApiKey(apikey: String) {
        _apiKey.value = apikey
    }

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email, password)
    }

    private fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

    fun onCreateOAuthKey(
        login: String,
        password: String,
        appKey: String
    ) {

        viewModelScope.launch {
            val data = timeTonicRepository.getOAuthKeyFromApi(
                login, password, appKey
            )

            dataStoreRepository.getCredentialsDataStore().collect { credentials ->
                if (data.isSuccess && _email.value == credentials.user
                    && _password.value == credentials.password
                    ) {
                    _dataCreateOAuthKey.value = data
                    _isLoginSuccess.value = true
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "email o password incorrect"
                    _isLoginSuccess.value = false
                }
            }
        }
    }

    fun onClearLoginFields() {
        _email.value = ""
        _password.value = ""
        onLoginChanged(_email.value.toString(), _password.value.toString())
    }

    fun onClearErrorMessage() {
        _errorMessage.value = null
    }

    fun onCleanLoginSuccess(){
        _dataCreateOAuthKey.value = null
        _isLoginSuccess.value = false
    }
}