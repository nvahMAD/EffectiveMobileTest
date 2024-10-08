package com.example.effectivemobile.di

import com.example.effectivemobile.presentation.factory.MainViewModelFactory
import com.example.effectivemobile.presentation.ui.activities.MainActivity
import com.example.effectivemobile.presentation.ui.fragments.FavouritesFragment
import com.example.effectivemobile.presentation.ui.fragments.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, ViewModelModule::class, DomainModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: FavouritesFragment)

    fun getViewModelFactory(): MainViewModelFactory
}