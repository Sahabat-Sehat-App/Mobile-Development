package com.capstone.sahabatsehat.ui.profile

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.sahabatsehat.data.api.ApiConfig
import com.capstone.sahabatsehat.data.model.UserModel
import com.capstone.sahabatsehat.data.response.LogoutResponse
import com.capstone.sahabatsehat.preferences.UserPreference
import com.capstone.sahabatsehat.util.Event
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel (private val pref: UserPreference): ViewModel()  {
    // TODO: Implement the ViewModel
    private val _logoutData = MutableLiveData<LogoutResponse>()
    val logoutdata: LiveData<LogoutResponse> = _logoutData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }
    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }



    fun logoutUserById(id: String, accessToken: String){
        _isLoading.value = true
        val service = ApiConfig.getApiService().logoutUser(id, "Bearer $accessToken")
        service.enqueue(object : Callback<LogoutResponse> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<LogoutResponse>,
                response: Response<LogoutResponse>
            ) {
                _isLoading.value = false

                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if(responseBody != null){
                            _logoutData.value = response.body()
                            _snackbarText.value = Event(response.body()?.message.toString())
                        }else{
                            _snackbarText.value = Event(response.body()?.message.toString())
                        }
                    }else{
                        _snackbarText.value = Event(response.body()?.message.toString())
                    }

            }
            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                _isLoading.value = false
                _snackbarText.value = Event(t.message.toString())
                Log.e("HomeFragmentViewModel", "onFailure: ${t.message.toString()}")
            }

        })
    }
}