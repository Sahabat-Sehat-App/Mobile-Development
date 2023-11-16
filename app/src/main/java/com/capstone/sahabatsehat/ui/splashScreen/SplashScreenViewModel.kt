package com.capstone.sahabatsehat.ui.splashScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.sahabatsehat.data.model.UserModel
import com.capstone.sahabatsehat.preferences.UserPreference

class SplashScreenViewModel (private val pref: UserPreference): ViewModel() {
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }
}