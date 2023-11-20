package com.capstone.sahabatsehat.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.sahabatsehat.data.api.ApiConfig
import com.capstone.sahabatsehat.data.response.RegisterResponse
import com.capstone.sahabatsehat.preferences.UserPreference
import com.capstone.sahabatsehat.util.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(private val pref: UserPreference): ViewModel() {

    private val _registerData = MutableLiveData<RegisterResponse>()
    val registerdata: LiveData<RegisterResponse> = _registerData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    fun registerUser(name: String, email: String, password: String, nohp: String){
        _isLoading.value = true
        val service = ApiConfig.getApiService().register(name, email, password, nohp)
        service.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                _isLoading.value = false
                if(response.code() == 400){
                    _snackbarText.value = Event(response.body()?.message.toString())

                }else if(response.code() == 401){
                    _snackbarText.value = Event(response.body()?.message.toString())

                }else{
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if(responseBody != null){
                            _registerData.value = response.body()
                            _snackbarText.value = Event(response.body()?.message.toString())
                        }else{
                            _snackbarText.value = Event(response.body()?.message.toString())
                        }
                    }else{
                        _snackbarText.value = Event(response.body()?.message.toString())
                    }
                }
            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _isLoading.value = false
                _snackbarText.value = Event(t.message.toString())
                Log.e("RegisterViewModel", "onFailure: ${t.message.toString()}")
            }

        })
    }
}