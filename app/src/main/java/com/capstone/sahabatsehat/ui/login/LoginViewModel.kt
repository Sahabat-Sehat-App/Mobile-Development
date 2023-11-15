package com.capstone.sahabatsehat.ui.login

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.sahabatsehat.data.api.ApiConfig
import com.capstone.sahabatsehat.data.preferences.Preferences
import com.capstone.sahabatsehat.data.preferences.UserModel
import com.capstone.sahabatsehat.data.response.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel(application: Application): AndroidViewModel(application) {
    val loginUser:MutableLiveData<LoginResponse> =
        MutableLiveData<LoginResponse>()


    lateinit var userPreferences: Preferences

    fun initialize(preferences: Preferences) {
        userPreferences = preferences
    }

    fun callloginapi(){
        viewModelScope.launch {

        }
    }
//    init {
//        login(email, password)
//    }
//        fun login(email:String, password:String){
//            val client= ApiConfig.getApiService().login(email, password)
//            client.enqueue(object :Callback<LoginResponse>{
//                @SuppressLint("SuspiciousIndentation")
//                override fun onResponse(
//                    call: Call<LoginResponse>,
//                    response: Response<LoginResponse>,
//                ) {
//                    if (response.isSuccessful){
//                    val loginUser=response.body()
//                        if (loginUser !=null){
//                            val name = loginUser.loginResult.name
//                            val nohp = loginUser.loginResult.nohp
//                            val isOnline = loginUser.loginResult.isOnline
//                            val accessToken = loginUser.loginResult.accessToken
//                            val userId = loginUser.loginResult.userId
//                            val email = loginUser.loginResult.email
//                            val alamat = loginUser.loginResult.alamat
//
//                            _loginUser.value=loginUser!!
//                            val user=UserModel(name,nohp, isOnline,accessToken,userId,email,alamat)
//                            userPreferences.saveUser(user)
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//
//            })
//        }
    companion object{
        private const val email = ""
        private const val password = ""
    }
}