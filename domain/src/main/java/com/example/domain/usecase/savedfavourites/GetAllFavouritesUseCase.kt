package com.example.domain.usecase.savedfavourites

import com.example.domain.model.FavouriteVacancyId
import com.example.domain.repository.FavouriteVacancyIdRepository
import io.reactivex.Flowable

class GetAllFavouritesUseCase(private val repository: FavouriteVacancyIdRepository) {

    fun execute() : Flowable<List<FavouriteVacancyId>>{

        return repository.getAllFavouriteVacancies()

    }

}