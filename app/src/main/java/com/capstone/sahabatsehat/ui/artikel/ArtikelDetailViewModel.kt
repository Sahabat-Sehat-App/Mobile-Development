package com.capstone.sahabatsehat.ui.artikel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.sahabatsehat.data.api.ApiConfig
import com.capstone.sahabatsehat.data.model.UserModel
import com.capstone.sahabatsehat.data.response.Artikel
import com.capstone.sahabatsehat.data.response.GetArtikelByIdResponse
import com.capstone.sahabatsehat.preferences.UserPreference
import com.capstone.sahabatsehat.util.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtikelDetailViewModel(private val pref: UserPreference): ViewModel() {

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    private val _artikelData = MutableLiveData<Artikel>()
    val artikelData: LiveData<Artikel> = _artikelData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    fun getArtikelById(id: String, accessToken: String){
        _isLoading.value = true
        val service = ApiConfig.getApiService().getArtikelById(id, "Bearer $accessToken")
        service.enqueue(object: Callback<GetArtikelByIdResponse> {
            override fun onResponse(
                call: Call<GetArtikelByIdResponse>,
                response: Response<GetArtikelByIdResponse>
            ) {
                _isLoading.value = false
                if (response.code() == 404){
                    _snackbarText.value = Event(response.body()?.message.toString())
                }else if(response.code() == 403){
                    _snackbarText.value = Event(response.body()?.message.toString())
                } else if(response.code() == 401){
                    _snackbarText.value = Event(response.body()?.message.toString())
                }else{
                    if(response.isSuccessful){
                        val responseBody = response.body()
                        if(responseBody != null){
                            _artikelData.value = response.body()?.artikel
                        }
                    }else{
                        _snackbarText.value = Event(response.body()?.message.toString())
                    }
                }
            }

            override fun onFailure(call: Call<GetArtikelByIdResponse>, t: Throwable) {
                _isLoading.value = false
                _snackbarText.value = Event(t.message.toString())
                Log.e("NewsDetailViewModel", "onFailure: ${t.message.toString()}")
            }

        })

    }
}