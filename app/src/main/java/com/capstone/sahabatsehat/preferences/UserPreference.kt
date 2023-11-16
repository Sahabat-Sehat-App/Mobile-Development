package com.capstone.sahabatsehat.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.capstone.sahabatsehat.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences


class UserPreference private constructor(private val dataStore: DataStore<Preferences>){
    fun getUser(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[USERID_KEY] ?: "",
                preferences[NAME_KEY] ?: "",
                preferences[EMAIL_KEY] ?: "",
                preferences[NOHP_KEY] ?: "",
                preferences[STATE_KEY] ?: false,
                preferences[ACCESSTOKEN_KEY] ?: "",
            )
        }
    }

    suspend fun login(user: UserModel){
        dataStore.edit { preferences ->
            preferences[USERID_KEY] = user.id
            preferences[NAME_KEY] = user.name
            preferences[EMAIL_KEY] = user.email
            preferences[NOHP_KEY] = user.nohp
            preferences[STATE_KEY] = user.isLogin
            preferences[ACCESSTOKEN_KEY] = user.accessToken
        }
    }

    suspend fun logout(){
        dataStore.edit { preferences ->
            preferences[USERID_KEY] = ""
            preferences[NAME_KEY] = ""
            preferences[EMAIL_KEY] = ""
            preferences[NOHP_KEY] = ""
            preferences[STATE_KEY] = false
            preferences[ACCESSTOKEN_KEY] = ""
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val USERID_KEY = stringPreferencesKey("id")
        private val NAME_KEY = stringPreferencesKey("name")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val NOHP_KEY = stringPreferencesKey("nohp")
        private val ACCESSTOKEN_KEY = stringPreferencesKey("accessToken")
        private val STATE_KEY = booleanPreferencesKey("isLogin")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference{
            return INSTANCE ?: synchronized(this){
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}