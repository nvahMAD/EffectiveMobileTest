package com.example.domain.usecase.savedfavourites

import com.example.domain.model.favouritesmodels.FavouriteVacancyId
import com.example.domain.repository.FavouriteVacancyIdRepository
import io.reactivex.Completable

class AddFavouriteVacancyUseCase (private val repository : FavouriteVacancyIdRepository) {

    fun execute(favouriteVacancyId: FavouriteVacancyId): Completable{

        return repository.addFavouriteVacancy(favouriteVacancyId)

    }

}