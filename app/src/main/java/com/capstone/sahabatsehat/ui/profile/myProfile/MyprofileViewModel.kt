package com.capstone.sahabatsehat.ui.profile.myProfile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.sahabatsehat.data.api.ApiConfig
import com.capstone.sahabatsehat.data.preferences.UserModel
import com.capstone.sahabatsehat.data.response.GetUserByIdResponse

import com.capstone.sahabatsehat.preferences.UserPreference
import com.capstone.sahabatsehat.util.Event
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyprofileViewModel(private val pref:UserPreference):ViewModel() {
    private val _getDatabyID = MutableLiveData<GetUserByIdResponse>()
    val getdata: LiveData<GetUserByIdResponse> = _getDatabyID

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText



    fun getUser(){
        viewModelScope.launch {
            pref.getUser()
        }
    }
    fun getUserById(id:String,accessToken:String){
        _isLoading.value=true
        val service=ApiConfig.getApiService().getUserById(id,accessToken)
        service.enqueue(object :Callback<GetUserByIdResponse>{
            override fun onResponse(
                call: Call<GetUserByIdResponse>,
                response: Response<GetUserByIdResponse>,
            ) {
                _isLoading.value=false
               if (response.isSuccessful){
                   val responseBody=response.body()
                   if (responseBody!=null){
                       _getDatabyID.value=response.body()
                       _snackbarText.value= Event(response.body()?.message.toString())
                   }else{
                       _snackbarText.value= Event(response.body()?.message.toString())
                   }
               }
            }

            override fun onFailure(call: Call<GetUserByIdResponse>, t: Throwable) {
                _isLoading.value = false
                _snackbarText.value = Event(t.message.toString())
                Log.e("LoginViewModel", "onFailure: ${t.message.toString()}")
            }

        })
    }
}