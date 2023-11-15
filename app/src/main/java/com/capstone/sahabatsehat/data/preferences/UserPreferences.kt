package com.capstone.sahabatsehat.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

class UserPreferences constructor(private val dataStore: DataStore<Preferences>) {
    companion object{
        @Volatile
        private  var INSTANCE:UserPreferences?=null
        private val ID= stringPreferencesKey("id")
        private val EMAIL= stringPreferencesKey("email")
        private val TOKEN= stringPreferencesKey("accessToken")
        private val STATE= booleanPreferencesKey("state")

        fun getInstance(dataStore:DataStore<Preferences>):UserPreferences{
            return INSTANCE?:synchronized(this){
                val instance= UserPreferences(dataStore)
                INSTANCE= instance
                instance
            }
        }

    }


    suspend fun saveUser(user:UserModel){
        dataStore.edit{pref->

        }
    }
}