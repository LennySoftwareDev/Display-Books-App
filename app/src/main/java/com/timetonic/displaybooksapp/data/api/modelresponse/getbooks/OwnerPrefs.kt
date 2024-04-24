package com.timetonic.displaybooksapp.data.api.modelresponse.getbooks


data class OwnerPrefs(
    val acceptExternalMsg: Boolean,
    val authorizeMemberBroadcast: Boolean,
    val fpAutoExport: Boolean,
    val notifyMobileConfidential: Boolean,
    val oCoverColor: String,
    val oCoverImg: String,
    val oCoverType: String,
    val oCoverUseLastImg: Boolean,
    val title: String
)