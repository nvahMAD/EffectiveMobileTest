package com.example.effectivemobile

import android.app.Application
import com.example.effectivemobile.di.AppComponent
import com.example.effectivemobile.di.AppModule
import com.example.effectivemobile.di.DaggerAppComponent

class EffectiveMobileApp(): Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getAppComponent(): AppComponent = appComponent

}