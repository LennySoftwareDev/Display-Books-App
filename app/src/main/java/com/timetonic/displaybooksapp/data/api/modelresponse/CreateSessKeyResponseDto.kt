package com.timetonic.displaybooksapp.data.api.modelresponse

import com.google.gson.annotations.SerializedName

data class CreateSessKeyResponseDto(
    @SerializedName("appName")val appName: String,
    @SerializedName("createdVNB")val createdVNB: String,
    @SerializedName("id")val id: String,
    @SerializedName("req")val req: String,
    @SerializedName("restrictions")val restrictions: RestrictionsDto,
    @SerializedName("sesskey")val sessKey: String,
    @SerializedName("status")val status: String
)