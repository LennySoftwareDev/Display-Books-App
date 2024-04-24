package com.timetonic.displaybooksapp.data.api.modelresponse

import com.google.gson.annotations.SerializedName

data class CreateOAuthKeyResponseDto(
    @SerializedName("createdVNB") val createdVNB: String,
    @SerializedName("id") val id: String,
    @SerializedName("o_u") val ou: String,
    @SerializedName("oauthkey") val oAuthKey: String,
    @SerializedName("req") val req: String,
    @SerializedName("status") val status: String
)