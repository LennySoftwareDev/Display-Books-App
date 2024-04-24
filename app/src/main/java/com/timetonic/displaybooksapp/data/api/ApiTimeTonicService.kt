package com.timetonic.displaybooksapp.data.api


import com.timetonic.displaybooks.utils.ConstantValues
import com.timetonic.displaybooksapp.data.api.modelresponse.CreateApiKeyResponseDto
import com.timetonic.displaybooksapp.data.api.modelresponse.CreateOAuthKeyResponseDto
import com.timetonic.displaybooksapp.data.api.modelresponse.CreateSessKeyResponseDto
import com.timetonic.displaybooksapp.data.api.modelresponse.getbooks.GetAllBooksResponseDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiTimeTonicService {

    @FormUrlEncoded
    @POST(ConstantValues.CREATE_APP_KEY)
    suspend fun createAppKey(
        @Field("appname") appName: String,
    ): CreateApiKeyResponseDto

    @FormUrlEncoded
    @POST(ConstantValues.CREATE_OAUTH_KEY)
    suspend fun createOAuthKey(
        @Field("login") login: String,
        @Field("pwd") password: String,
        @Field("appkey") appKey: String
    ): CreateOAuthKeyResponseDto

    @FormUrlEncoded
    @POST(ConstantValues.CREATE_SESS_KEY)
    suspend fun createSessKey(
        @Field("o_u") ou: String,
        @Field("oauthkey") oAuthKey: String,
        @Field("restrictions") restriction: String
    ): CreateSessKeyResponseDto

    @GET(ConstantValues.GET_ALL_BOOKS)
    suspend fun getAllBooks(
        @Query("o_u") ou: String,
        @Query("u_c") uc: String,
        @Query("sesskey") sessKey: String
    ): GetAllBooksResponseDto
}