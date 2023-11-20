package com.capstone.sahabatsehat.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.sahabatsehat.data.api.ApiConfig
import com.capstone.sahabatsehat.data.model.UserModel
import com.capstone.sahabatsehat.data.response.ArtikelItem
import com.capstone.sahabatsehat.data.response.GetAllArtikelResponse
import com.capstone.sahabatsehat.data.response.GetUserByIdResponse
import com.capstone.sahabatsehat.preferences.UserPreference
import com.capstone.sahabatsehat.util.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentViewModel(private val pref: UserPreference) : ViewModel() {

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    private val _userData = MutableLiveData<GetUserByIdResponse>()
    val userData: LiveData<GetUserByIdResponse> = _userData

    private val _artikelData = MutableLiveData<List<ArtikelItem>>()
    val artikelData: LiveData<List<ArtikelItem>> = _artikelData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText


    fun getUserById(id: String, accessToken: String){
        _isLoading.value = true
        val service = ApiConfig.getApiService().getUserById(id, "Bearer $accessToken")
        service.enqueue(object : Callback<GetUserByIdResponse>{
            override fun onResponse(
                call: Call<GetUserByIdResponse>,
                response: Response<GetUserByIdResponse>
            ) {
                _isLoading.value = false
                if(response.code() == 404){
                    _snackbarText.value = Event(response.body()?.message.toString())
                }else{
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if(responseBody != null){
                            _userData.value = response.body()
                            _snackbarText.value = Event(response.body()?.message.toString())
                        }else{
                            _snackbarText.value = Event(response.body()?.message.toString())
                        }
                    }
                }
            }
            override fun onFailure(call: Call<GetUserByIdResponse>, t: Throwable) {
                _isLoading.value = false
                _snackbarText.value = Event(t.message.toString())
                Log.e("HomeFragmentViewModel", "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getAllNews(accessToken: String){
        _isLoading.value = true
        val service = ApiConfig.getApiService().getAllArtikel("Bearer $accessToken")
        service.enqueue(object: Callback<GetAllArtikelResponse> {
            override fun onResponse(
                call: Call<GetAllArtikelResponse>,
                response: Response<GetAllArtikelResponse>
            ) {
                _isLoading.value = false
                if(response.code() == 403){
                    _snackbarText.value = Event(response.body()?.message.toString())
                }else if(response.code() == 401){
                    _snackbarText.value = Event(response.body()?.message.toString())
                }else{
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if(responseBody != null){
                            _artikelData.postValue(responseBody.artikel)
                            _snackbarText.value = Event(response.body()?.message.toString())
                        }
                    }else{
                        _snackbarText.value = Event(response.body()?.message.toString())
                    }
                }
            }

            override fun onFailure(call: Call<GetAllArtikelResponse>, t: Throwable) {
                _isLoading.value = false
                _snackbarText.value = Event(t.message.toString())
                Log.e("HomeFragmentViewModel", "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun searchArtikelByType(accessToken: String, jenisArtikel: String){
        _isLoading.value = true
        val service = ApiConfig.getApiService().searchArtikelByType("Bearer $accessToken", jenisArtikel)
        service.enqueue(object: Callback<GetAllArtikelResponse>{
            override fun onResponse(
                call: Call<GetAllArtikelResponse>,
                response: Response<GetAllArtikelResponse>
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
                            _artikelData.value = response.body()?.artikel
                            _snackbarText.value = Event(response.body()?.message.toString())
                        }else{
                            _snackbarText.value = Event(response.body()?.message.toString())
                        }
                    }else{
                        _snackbarText.value = Event(response.body()?.message.toString())
                    }
                }
            }

            override fun onFailure(call: Call<GetAllArtikelResponse>, t: Throwable) {
                _isLoading.value = false
                _snackbarText.value = Event(t.message.toString())
                Log.e("ExploreViewModel", "onFailure: ${t.message.toString()}")
            }

        })
    }
}