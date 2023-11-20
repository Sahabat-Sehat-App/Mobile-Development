package com.capstone.sahabatsehat.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.sahabatsehat.preferences.UserPreference
import com.capstone.sahabatsehat.ui.artikel.ArtikelDetailViewModel
import com.capstone.sahabatsehat.ui.home.HomeFragmentViewModel
import com.capstone.sahabatsehat.ui.layanan.ListLayananDoctorViewModel
import com.capstone.sahabatsehat.ui.login.LoginViewModel
import com.capstone.sahabatsehat.ui.profile.ProfileViewModel
import com.capstone.sahabatsehat.ui.register.RegisterViewModel
import com.capstone.sahabatsehat.ui.splashScreen.SplashScreenViewModel

class ViewModelFactory(private val pref: UserPreference, private val context: Context): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(pref) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(pref) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(pref) as T
            }
            modelClass.isAssignableFrom(SplashScreenViewModel::class.java) -> {
                SplashScreenViewModel(pref) as T
            }
            modelClass.isAssignableFrom(HomeFragmentViewModel::class.java) -> {
                HomeFragmentViewModel(pref) as T
            }
            modelClass.isAssignableFrom(ArtikelDetailViewModel::class.java) -> {
                ArtikelDetailViewModel(pref) as T
            }
            modelClass.isAssignableFrom(ListLayananDoctorViewModel::class.java) -> {
                ListLayananDoctorViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}