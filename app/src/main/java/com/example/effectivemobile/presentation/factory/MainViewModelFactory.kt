package com.example.effectivemobile.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.savedfavourites.AddFavouriteVacancyUseCase
import com.example.domain.usecase.savedfavourites.GetAllFavouritesUseCase
import com.example.domain.usecase.savedfavourites.RemoveFavouriteVacancyUseCase
import com.example.effectivemobile.presentation.viewmodel.MainViewModel

class MainViewModelFactory(
    private val addFavouriteVacancyUseCase: AddFavouriteVacancyUseCase,
    private val removeFavouriteVacancyUseCase: RemoveFavouriteVacancyUseCase,
    private val getAllFavouritesUseCase: GetAllFavouritesUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                addFavouriteVacancyUseCase,
                removeFavouriteVacancyUseCase,
                getAllFavouritesUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}