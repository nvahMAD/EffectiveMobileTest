package com.example.domain.usecase.savedfavourites

import com.example.domain.model.favouritesmodels.FavouriteVacancyId
import com.example.domain.repository.FavouriteVacancyIdRepository
import io.reactivex.Completable

class RemoveFavouriteVacancyIdUseCase(private val repository: FavouriteVacancyIdRepository) {

    fun execute(favouriteVacancyId: FavouriteVacancyId): Completable {

        return repository.removeFavouriteVacancyId(favouriteVacancyId)

    }

}