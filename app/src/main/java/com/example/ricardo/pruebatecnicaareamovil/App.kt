package com.example.ricardo.pruebatecnicaareamovil

import android.app.Activity
import android.support.multidex.MultiDexApplication
import com.example.ricardo.pruebatecnicaareamovil.data.api.ApiClient
import com.example.ricardo.pruebatecnicaareamovil.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.Realm
import okhttp3.HttpUrl
import javax.inject.Inject

class App : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = injector

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        ApiClient.init(HttpUrl.parse(getString(R.string.url))!!)
        Realm.init(this)
    }



}