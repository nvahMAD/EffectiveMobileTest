package com.example.domain.usecase.savedfavourites

import com.example.domain.model.apimodels.Vacancy
import com.example.domain.model.favouritesmodels.FavouriteVacancyId
import com.example.domain.repository.FavouriteVacancyIdRepository
import io.reactivex.Flowable
import io.reactivex.Single

class GetAllFavouritesIdsUseCase(private val repository: FavouriteVacancyIdRepository) {

    fun execute() : Single<List<FavouriteVacancyId>>{

        return repository.getAllFavouriteVacanciesIds()

    }

}