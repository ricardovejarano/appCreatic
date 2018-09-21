package com.example.ricardo.pruebatecnicaareamovil.ui.main

import android.arch.lifecycle.ViewModel
import com.example.ricardo.pruebatecnicaareamovil.data.api.Api
import com.example.ricardo.pruebatecnicaareamovil.data.api.ApiClient
import com.example.ricardo.pruebatecnicaareamovil.data.model.User
import com.example.ricardo.pruebatecnicaareamovil.util.applySchedulers
import io.reactivex.Observable
import javax.inject.Inject

class MainViewModel@Inject constructor(): ViewModel() {

    val api: Api = ApiClient.create()

    fun getUser(username: String): Observable<User> =
            api.getUser(username)
                    .map {
                        it
                    }
                    .applySchedulers()
}