package com.example.ricardo.pruebatecnicaareamovil.di.components


import android.app.Application
import com.example.ricardo.pruebatecnicaareamovil.App
import com.example.ricardo.pruebatecnicaareamovil.di.ActivityBuilders
import com.example.ricardo.pruebatecnicaareamovil.di.modules.AppModule
import com.example.ricardo.pruebatecnicaareamovil.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    (AndroidInjectionModule::class),
    (AppModule::class),
    (ActivityBuilders::class),
    (ViewModelModule::class)
])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicacion(application: Application): Builder

        fun build(): AppComponent
    }

}
