package com.timetonic.displaybooksapp.data.api.repository

import com.timetonic.displaybooksapp.data.api.ApiTimeTonicServiceImpl
import com.timetonic.displaybooksapp.data.api.modelresponse.CreateApiKeyResponseDto
import com.timetonic.displaybooksapp.data.api.modelresponse.CreateOAuthKeyResponseDto
import com.timetonic.displaybooksapp.data.api.modelresponse.CreateSessKeyResponseDto
import com.timetonic.displaybooksapp.data.api.modelresponse.getbooks.GetAllBooksResponseDto
import javax.inject.Inject

class TimeTonicRepository @Inject constructor(private val api: ApiTimeTonicServiceImpl) {
    suspend fun getApiKeyFromApi(): Result<CreateApiKeyResponseDto> {
        return api.createAppKey()
    }

    suspend fun getOAuthKeyFromApi(
        login: String,
        password: String,
        appKey: String
    ): Result<CreateOAuthKeyResponseDto> {
        return api.createOAuthKey(login, password, appKey)
    }

    suspend fun getSessKeyFromApi(
        ou: String,
        oAuthKey: String,
        restriction: String
    ): Result<CreateSessKeyResponseDto> {
        return api.createSessKey(ou, oAuthKey, restriction)
    }

    suspend fun getAllBooksFromApi(
        ou: String,
        uc: String,
        sessKey: String
    ): Result<GetAllBooksResponseDto> {
        return api.getAllBooks(ou, uc, sessKey)
    }
}
