package com.example.ricardo.pruebatecnicaareamovil.di


import com.example.ricardo.pruebatecnicaareamovil.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilders{

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActiviry(): MainActivity



}
