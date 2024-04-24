package com.timetonic.displaybooksapp.data.api.modelresponse

import com.google.gson.annotations.SerializedName

data class CreateApiKeyResponseDto(
    @SerializedName("status") val status: String,
    @SerializedName("appkey") var appKey: String,
    @SerializedName("id") val id: String,
    @SerializedName("createdVNB") val createdVNB: String,
    @SerializedName("req") val req: String
)