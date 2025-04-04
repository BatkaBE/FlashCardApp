package com.example.wordapp.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val SETTINGS_PREFERENCES_NAME = "settings_prefs"
private val Context.dataStore by preferencesDataStore(name = SETTINGS_PREFERENCES_NAME)

class SettingsDataStore(private val context: Context) {

    private val DISPLAY_OPTION_KEY = stringPreferencesKey("display_option")

    val selectedOption: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[DISPLAY_OPTION_KEY] ?: "Монгол болон гадаад үгийг зэрэг харуулах" }

    suspend fun saveSelectedOption(option: String) {
        context.dataStore.edit { preferences ->
            preferences[DISPLAY_OPTION_KEY] = option
        }
    }
}
