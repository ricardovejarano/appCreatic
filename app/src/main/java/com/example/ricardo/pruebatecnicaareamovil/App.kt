package com.example.ricardo.pruebatecnicaareamovil

import android.support.multidex.MultiDexApplication
import com.example.ricardo.pruebatecnicaareamovil.data.api.ApiClient
import okhttp3.HttpUrl

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        ApiClient.init(HttpUrl.parse("https://api.github.com/users")!!)
    }



}