package com.capstone.sahabatsehat.data.preferences

import android.content.Context
import android.content.SharedPreferences

class Preferences(context:Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "user_preferences",
        Context.MODE_PRIVATE
    )


    fun saveUser(user: UserModel) {
        with(sharedPreferences.edit()) {
            putString("name", user.name)
            putString("nohp", user.nohp)
            putBoolean("isOnline", user.isOnline)
            putString("accessToken", user.accessToken)
            putString("userId", user.userId)
            putString("email", user.email)
            putString("alamat", user.alamat)
            apply()
        }
    }
}