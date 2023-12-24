package com.example.appendereco.api

import com.example.appendereco.model.Address
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CorreiosAPI {

    @GET("{cep}/json/")
    suspend fun getAddress(@Path("cep") cep: String): Response<Address>

    companion object {

        private const val BASE_URL = "https://viacep.com.br/ws/"

        private var retrofitService: CorreiosAPI? = null


        fun getInstance(): CorreiosAPI {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()


            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(CorreiosAPI::class.java)

            }
            return retrofitService!!
        }

    }
}