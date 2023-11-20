package com.capstone.sahabatsehat.ui.layanan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.sahabatsehat.data.api.ApiConfig
import com.capstone.sahabatsehat.data.model.UserModel
import com.capstone.sahabatsehat.data.response.GetAllArtikelResponse
import com.capstone.sahabatsehat.data.response.GetUserByIdResponse
import com.capstone.sahabatsehat.data.response.LayananItem
import com.capstone.sahabatsehat.data.response.SearchLayananByTypeResponse
import com.capstone.sahabatsehat.preferences.UserPreference
import com.capstone.sahabatsehat.util.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListLayananDoctorViewModel(private val pref: UserPreference): ViewModel() {

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    private val _layananData = MutableLiveData<List<LayananItem>>()
    val layananData: LiveData<List<LayananItem>> = _layananData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    fun searchLayananByType(accessToken: String, jenisLayanan: String){
        _isLoading.value = true
        val service = ApiConfig.getApiService().searchLayananByType("Bearer $accessToken", jenisLayanan)
        service.enqueue(object: Callback<SearchLayananByTypeResponse> {
            override fun onResponse(
                call: Call<SearchLayananByTypeResponse>,
                response: Response<SearchLayananByTypeResponse>
            ) {
                _isLoading.value = false
                if(response.code() == 404){
                    _snackbarText.value = Event(response.body()?.message.toString())
                }else if(response.code() == 403){
                    _snackbarText.value = Event(response.body()?.message.toString())
                }else if(response.code() == 401){
                    _snackbarText.value = Event(response.body()?.message.toString())
                }else{
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if(responseBody != null){
                            _layananData.value = response.body()?.layanan
                            _snackbarText.value = Event(response.body()?.message.toString())
                        }else{
                            _snackbarText.value = Event(response.body()?.message.toString())
                        }
                    }else{
                        _snackbarText.value = Event(response.body()?.message.toString())
                    }
                }
            }

            override fun onFailure(call: Call<SearchLayananByTypeResponse>, t: Throwable) {
                _isLoading.value = false
                _snackbarText.value = Event(t.message.toString())
                Log.e("ExploreViewModel", "onFailure: ${t.message.toString()}")
            }

        })
    }

}