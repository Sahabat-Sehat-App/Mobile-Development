package com.capstone.sahabatsehat.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.sahabatsehat.data.api.ApiService
import com.capstone.sahabatsehat.data.response.LoginRequest
import com.capstone.sahabatsehat.data.response.LoginResponse
import com.capstone.sahabatsehat.data.response.LoginResult
import retrofit2.Response

class UserRepository() {

     suspend fun loginUser(loginRequst:LoginRequest): Response<LoginResponse>?{
         return null
     }
}