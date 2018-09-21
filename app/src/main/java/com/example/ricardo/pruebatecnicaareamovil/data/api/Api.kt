package com.example.ricardo.pruebatecnicaareamovil.data.api

import com.example.ricardo.pruebatecnicaareamovil.data.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("${ApiClient.API_QUERY}/{username}")
    fun getUser(@Path("username")username: String): Observable<User>
}