package com.example.effectivemobile.di

import com.example.domain.usecase.apiusecases.GetApiResponseUseCase
import com.example.domain.usecase.savedfavourites.AddFavouriteVacancyUseCase
import com.example.domain.usecase.savedfavourites.GetAllFavouritesUseCase
import com.example.domain.usecase.savedfavourites.RemoveFavouriteVacancyUseCase
import com.example.effectivemobile.presentation.factory.MainViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(
        addFavouriteVacancyUseCase: AddFavouriteVacancyUseCase,
        removeFavouriteVacancyUseCase: RemoveFavouriteVacancyUseCase,
        getAllFavouritesUseCase: GetAllFavouritesUseCase,
        getApiResponseUseCase: GetApiResponseUseCase
    ): MainViewModelFactory {
        return MainViewModelFactory(
            addFavouriteVacancyUseCase,
            removeFavouriteVacancyUseCase,
            getAllFavouritesUseCase,
            getApiResponseUseCase
        )
    }

}