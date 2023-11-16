package com.capstone.sahabatsehat.data.api

import com.capstone.sahabatsehat.data.response.GetUserByIdResponse
import com.capstone.sahabatsehat.data.response.LoginResponse
import com.capstone.sahabatsehat.data.response.LogoutResponse
import com.capstone.sahabatsehat.data.response.RegisterResponse
import com.capstone.sahabatsehat.data.response.UpdateUserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @FormUrlEncoded
    @POST("api/user/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("api/user/register")
    fun register(
        @Field("name") name: String,
        @Field("email") username: String,
        @Field("password") email: String,
        @Field("nohp") password: String
    ): Call<RegisterResponse>

    @POST("api/user/logout/{id}")
    fun logoutUser(
        @Path("id") id: String,
        @Header("Authorization") accessToken: String
    ): Call<LogoutResponse>

    @GET("api/user/{id}")
    fun getUserById(
        @Path("id") id: String,
        @Header("Authorization") accessToken: String
    ): Call<GetUserByIdResponse>

    @Multipart
    @PUT("api/user/{id}")
    fun updateUserById(
        @Path("id") id: String,
        @Header("Authorization") accessToken: String,
        @Part file: MultipartBody.Part? = null,
        @Part("alamat") alamat: RequestBody,
        @Part("nohp") nohp: RequestBody,
        @Part("name") name: RequestBody,
    ): Call<UpdateUserResponse>

    companion object {
        fun getApi(): ApiService? {
            return ApiConfig.getApiService()
        }
    }
}