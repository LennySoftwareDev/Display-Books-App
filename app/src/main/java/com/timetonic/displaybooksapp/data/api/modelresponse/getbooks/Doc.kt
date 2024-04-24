package com.timetonic.displaybooksapp.data.api.modelresponse.getbooks


data class Doc(
    val del: Boolean,
    val ext: String,
    val id: Int,
    val internName: String,
    val originName: String,
    val size: Int,
    val type: String,
    val uuid: String
)