package com.example.ricardo.pruebatecnicaareamovil.data.api

import com.example.ricardo.pruebatecnicaareamovil.data.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("/{username}")
    fun getAllDatasets(@Path("username")username: String): Observable<User>
}