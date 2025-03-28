package edu.fullerton.fz.cs411.simpleapp_spring24

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MyDataRepository private constructor(private val myDataStore: DataStore<Preferences>) {
    val savedCounter: Flow<Int> = this.myDataStore.data.map {
        it[KEY_NUMERIC_COUNTER] ?: 0
    }
    val savedHappy: Flow<Boolean> = this.myDataStore.data.map {
        it[KEY_BOOLEAN_HAPPY] ?: false
    }
    private suspend fun saveIntValue(key: Preferences.Key<Int>, value: Int) {
        this.myDataStore.edit { prefs ->
            prefs[key] = value
        }
    }
    private suspend fun saveBooleanValue(key: Preferences.Key<Boolean>, value: Boolean) {
        this.myDataStore.edit { prefs ->
            prefs[key] = value
        }
    }
    suspend fun saveCounter(value: Int) {
        saveIntValue(KEY_NUMERIC_COUNTER, value)
    }
    suspend fun saveHappy(value: Boolean) {
        saveBooleanValue(KEY_BOOLEAN_HAPPY, value)
    }

    companion object {
        private const val PREFERENCES_DATA_FILE_NAME = "values"
        private var INSTANCE: MyDataRepository? = null
        private val KEY_NUMERIC_COUNTER: Preferences.Key<Int> = intPreferencesKey("counter")
        private val KEY_BOOLEAN_HAPPY: Preferences.Key<Boolean>  = booleanPreferencesKey("happy")
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                val dataStore = PreferenceDataStoreFactory.create {
                    context.preferencesDataStoreFile(PREFERENCES_DATA_FILE_NAME)
                }
                INSTANCE = MyDataRepository(dataStore)
            }
        }
        fun getInstance(): MyDataRepository  {
            return INSTANCE ?: throw IllegalStateException("MyPreferencesRepository singleton instance INSTANCE has not been initialized yet through a call ro the function initialize()")
        }
    }
}