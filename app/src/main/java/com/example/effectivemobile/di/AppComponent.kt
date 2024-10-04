package com.example.effectivemobile.di

import com.example.effectivemobile.presentation.factory.MainViewModelFactory
import com.example.effectivemobile.presentation.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, ViewModelModule::class, DomainModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun getViewModelFactory(): MainViewModelFactory
}