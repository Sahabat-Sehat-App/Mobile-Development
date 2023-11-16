package com.capstone.sahabatsehat.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.sahabatsehat.data.api.ApiConfig
import com.capstone.sahabatsehat.data.model.UserModel
import com.capstone.sahabatsehat.data.response.LoginResponse
import com.capstone.sahabatsehat.preferences.UserPreference
import com.capstone.sahabatsehat.util.Event
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val pref: UserPreference): ViewModel() {

    private val _loginData = MutableLiveData<LoginResponse>()
    val logindata: LiveData<LoginResponse> = _loginData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    fun login(user: UserModel){
        viewModelScope.launch {
            pref.login(user)
        }
    }

    fun loginUser(email: String, password: String){
        _isLoading.value = true
        val service = ApiConfig.getApiService().login(email, password)
        service.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _isLoading.value = false
                if(response.code() == 404){
                    _snackbarText.postValue(Event("User Not found."))
                }else if(response.code() == 401){
                    _snackbarText.postValue(Event("Invalid Password!"))
                }else if(response.code() == 400){
                    _snackbarText.postValue(Event("Password can not be empty!"))
                }else{
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if(responseBody != null){
                            _loginData.value = response.body()
                            _snackbarText.value = Event(response.body()?.message.toString())
                        }else{
                            _snackbarText.value = Event(response.body()?.message.toString())
                        }
                    }
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                _snackbarText.value = Event(t.message.toString())
                Log.e("LoginViewModel", "onFailure: ${t.message.toString()}")
            }

        })
    }
}