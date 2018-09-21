package com.example.ricardo.pruebatecnicaareamovil.data.api

import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    const val API_QUERY = "/users"

    lateinit var client: Retrofit

    fun init(url: HttpUrl) {

        client = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                        .create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    inline fun <reified T> create(): T = client.create(T::class.java)
}