package com.example.effectivemobile.di

import com.example.domain.repository.ApiResponseRepository
import com.example.domain.usecase.apiusecases.GetApiResponseUseCase
import com.example.domain.usecase.functionalusecases.GetMonthNameUseCase
import com.example.domain.usecase.functionalusecases.GetPeopleDeclensionUseCase
import com.example.domain.usecase.functionalusecases.GetVacancyDeclensionUseCase
import com.example.domain.usecase.savedfavourites.AddFavouriteVacancyIdUseCase
import com.example.domain.usecase.savedfavourites.GetAllFavouritesIdsUseCase
import com.example.domain.usecase.savedfavourites.GetAllFavouritesVacanciesUseCase
import com.example.domain.usecase.savedfavourites.RemoveFavouriteVacancyIdUseCase
import com.example.effectivemobile.presentation.factory.MainViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(
        addFavouriteVacancyIdUseCase: AddFavouriteVacancyIdUseCase,
        removeFavouriteVacancyIdUseCase: RemoveFavouriteVacancyIdUseCase,
        getAllFavouritesIdsUseCase: GetAllFavouritesIdsUseCase,
        getApiResponseUseCase: GetApiResponseUseCase,
        apiResponseRepository: ApiResponseRepository,
        getAllFavouritesVacanciesUseCase: GetAllFavouritesVacanciesUseCase,
        getMonthNameUseCase: GetMonthNameUseCase,
        getPeopleDeclensionUseCase: GetPeopleDeclensionUseCase,
        getVacancyDeclensionUseCase: GetVacancyDeclensionUseCase
    ): MainViewModelFactory {
        return MainViewModelFactory(
            addFavouriteVacancyIdUseCase,
            removeFavouriteVacancyIdUseCase,
            getAllFavouritesIdsUseCase,
            getApiResponseUseCase,
            apiResponseRepository,
            getAllFavouritesVacanciesUseCase,
            getMonthNameUseCase,
            getPeopleDeclensionUseCase,
            getVacancyDeclensionUseCase
        )
    }

}