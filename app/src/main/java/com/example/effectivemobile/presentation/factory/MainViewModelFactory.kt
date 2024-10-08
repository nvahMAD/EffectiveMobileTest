package com.example.effectivemobile.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.repository.ApiResponseRepository
import com.example.domain.usecase.apiusecases.GetApiResponseUseCase
import com.example.domain.usecase.functionalusecases.GetMonthNameUseCase
import com.example.domain.usecase.functionalusecases.GetPeopleDeclensionUseCase
import com.example.domain.usecase.functionalusecases.GetVacancyDeclensionUseCase
import com.example.domain.usecase.savedfavourites.AddFavouriteVacancyIdUseCase
import com.example.domain.usecase.savedfavourites.GetAllFavouritesIdsUseCase
import com.example.domain.usecase.savedfavourites.GetAllFavouritesVacanciesUseCase
import com.example.domain.usecase.savedfavourites.RemoveFavouriteVacancyIdUseCase
import com.example.effectivemobile.presentation.viewmodel.MainViewModel

class MainViewModelFactory(
    private val addFavouriteVacancyIdUseCase: AddFavouriteVacancyIdUseCase,
    private val removeFavouriteVacancyIdUseCase: RemoveFavouriteVacancyIdUseCase,
    private val getAllFavouritesIdsUseCase: GetAllFavouritesIdsUseCase,
    private val getApiResponseUseCase: GetApiResponseUseCase,
    private val apiResponseRepository: ApiResponseRepository,
    private val getAllFavouritesVacanciesUseCase: GetAllFavouritesVacanciesUseCase,
    private val getMonthNameUseCase: GetMonthNameUseCase,
    private val getPeopleDeclensionUseCase: GetPeopleDeclensionUseCase,
    private val getVacancyDeclensionUseCase: GetVacancyDeclensionUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                addFavouriteVacancyIdUseCase,
                removeFavouriteVacancyIdUseCase,
                getAllFavouritesIdsUseCase,
                getApiResponseUseCase,
                apiResponseRepository,
                getAllFavouritesVacanciesUseCase,
                getMonthNameUseCase,
                getPeopleDeclensionUseCase,
                getVacancyDeclensionUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}