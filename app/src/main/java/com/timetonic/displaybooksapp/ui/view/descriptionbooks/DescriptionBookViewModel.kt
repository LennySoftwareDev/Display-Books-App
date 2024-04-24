package com.timetonic.displaybooksapp.ui.view.descriptionbooks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timetonic.displaybooksapp.data.api.modelresponse.CreateSessKeyResponseDto
import com.timetonic.displaybooksapp.data.api.modelresponse.getbooks.GetAllBooksResponseDto
import com.timetonic.displaybooksapp.data.api.repository.TimeTonicRepository
import com.timetonic.displaybooksapp.data.local.model.SessKeyPreferencesDto
import com.timetonic.displaybooksapp.data.local.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DescriptionBookViewModel @Inject constructor(
    private val appRepository: TimeTonicRepository,
    private val dataStoreRepository: DataStoreRepository
    ) : ViewModel() {

    private val _dataCreateSessKey = MutableLiveData<Result<CreateSessKeyResponseDto>>()
    val dataCreateSessKey : LiveData<Result<CreateSessKeyResponseDto>> = _dataCreateSessKey

    private val _dataGetAllBooks = MutableLiveData<Result<GetAllBooksResponseDto>>()
    val dataGetAllBooks : LiveData<Result<GetAllBooksResponseDto>> = _dataGetAllBooks

    private val _oAuthKey = MutableLiveData<String>()
    val oAuthKey: LiveData<String> = _oAuthKey

    private val _sessKey = MutableLiveData<String>()
    val sessKey: LiveData<String> = _sessKey

    private val _isLoadingBooks = MutableLiveData<Boolean>()
    val isLoadingBooks: LiveData<Boolean> = _isLoadingBooks

    private val _isSessKeySuccess = MutableLiveData<Boolean>()
    val isSessKeySuccess: LiveData<Boolean> = _isSessKeySuccess

    private var sessKeyPreferencesDto: SessKeyPreferencesDto = SessKeyPreferencesDto(sessKey = "")

    fun getOAuthKey(oAuthKey: String){
        _oAuthKey.value = oAuthKey
    }

    fun getSessKey(sessKey: String){
        _sessKey.value = sessKey
    }

    fun onCreateSessKey(
        ou: String,
        oAuthKey: String,
        restriction: String
    ){
        viewModelScope.launch {
            val data = appRepository.getSessKeyFromApi(
                ou,oAuthKey,restriction
            )
            if (data.isSuccess){
                _dataCreateSessKey.value = data
                _isSessKeySuccess.value = true
            }
        }
    }

    fun saveSessKey(){
        this.sessKeyPreferencesDto = SessKeyPreferencesDto(
            sessKey = _sessKey.value.toString()
        )

        viewModelScope.launch {
            sessKeyPreferencesDto.let { dataStoreRepository.saveSessKeyDataStore(it) }
        }
    }

    fun getAllBooks(
        ou: String,
        uc: String,
        sessKey: String
    ){
        viewModelScope.launch {
            val dataBooks = appRepository.getAllBooksFromApi(ou,uc,sessKey)
            _isLoadingBooks.value = true

            if(dataBooks.isSuccess){
                _isLoadingBooks.value = true

                _dataGetAllBooks.value = dataBooks
            }else{
                _isLoadingBooks.value = false
            }
        }
    }
}