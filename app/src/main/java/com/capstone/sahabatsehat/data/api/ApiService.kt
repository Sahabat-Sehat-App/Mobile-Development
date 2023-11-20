package com.capstone.sahabatsehat.data.api

import com.capstone.sahabatsehat.data.response.GetAllArtikelResponse
import com.capstone.sahabatsehat.data.response.GetArtikelByIdResponse
import com.capstone.sahabatsehat.data.response.GetUserByIdResponse
import com.capstone.sahabatsehat.data.response.LoginResponse
import com.capstone.sahabatsehat.data.response.LogoutResponse
import com.capstone.sahabatsehat.data.response.RegisterPatientResponse
import com.capstone.sahabatsehat.data.response.RegisterResponse
import com.capstone.sahabatsehat.data.response.SearchLayananByTypeResponse
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
import retrofit2.http.Query

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

    @GET("api/artikel/")
    fun getAllArtikel(
        @Header("Authorization") accessToken: String,
    ): Call<GetAllArtikelResponse>

    @GET("api/artikel/{id}")
    fun getArtikelById(
        @Path("id") id: String,
        @Header("Authorization") accessToken: String
    ): Call<GetArtikelByIdResponse>

    @GET("/artikel/search/")
    fun searchArtikelByType(
        @Header("Authorization") accessToken: String,
        @Query("jenisArtikel") jenisArtikel: String
    ): Call<GetAllArtikelResponse>

    @GET("/layanan/search/")
    fun searchLayananByType(
        @Header("Authorization") accessToken: String,
        @Query("jenisLayanan") jenisLayanan: String
    ): Call<SearchLayananByTypeResponse>

    @FormUrlEncoded
    @POST("api/patient/register")
    fun registerPatient(
        @Field("name") name: String,
        @Field("keluhan") username: String,
        @Field("password") email: String,
        @Field("nohp") password: String
    ): Call<RegisterPatientResponse>
}