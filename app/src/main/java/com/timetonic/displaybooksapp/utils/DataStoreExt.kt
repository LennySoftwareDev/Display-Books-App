package com.timetonic.displaybooks.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

const val USER_PREFERENCES = "USER_PREFERENCES"

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES)