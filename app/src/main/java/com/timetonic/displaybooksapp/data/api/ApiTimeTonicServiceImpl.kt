package com.timetonic.displaybooksapp.data.api

import com.timetonic.displaybooksapp.data.api.modelresponse.CreateApiKeyResponseDto
import com.timetonic.displaybooksapp.data.api.modelresponse.CreateOAuthKeyResponseDto
import com.timetonic.displaybooksapp.data.api.modelresponse.CreateSessKeyResponseDto
import com.timetonic.displaybooksapp.data.api.modelresponse.getbooks.GetAllBooksResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiTimeTonicServiceImpl @Inject constructor(private val api: ApiTimeTonicService) {
    suspend fun createAppKey(): Result<CreateApiKeyResponseDto> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.createAppKey("app")
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun createOAuthKey(
        login: String,
        password: String,
        appKey: String
    ): Result<CreateOAuthKeyResponseDto> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.createOAuthKey(login, password, appKey)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun createSessKey(
        ou: String,
        oAuthKey: String,
        restriction: String
    ): Result<CreateSessKeyResponseDto> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.createSessKey(ou,oAuthKey,restriction)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getAllBooks(
        ou : String,
        uc : String,
        sessKey: String
    ) : Result<GetAllBooksResponseDto>{
        return withContext(Dispatchers.IO){
            try {
                val response = api.getAllBooks(ou,uc,sessKey)
                Result.success(response)
            }catch (e:Exception){
                Result.failure(e)
            }
        }
    }
}
