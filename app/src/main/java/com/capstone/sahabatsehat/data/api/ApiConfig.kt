package com.capstone.sahabatsehat.data.api

class ApiConfig {
    companion object{
        const val BASE_URL = "sahabat.innoji26.xyz/"
        const val URL_AVATAR = BASE_URL + "public/uploads/"

        fun getApiService():ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}