package com.example.domain.usecase.savedfavourites

import com.example.domain.model.FavouriteVacancyId
import com.example.domain.repository.FavouriteVacancyIdRepository
import io.reactivex.Completable

class RemoveFavouriteVacancyUseCase(private val repository: FavouriteVacancyIdRepository) {

    fun execute(favouriteVacancyId: FavouriteVacancyId): Completable {

        return repository.removeFavouriteVacancy(favouriteVacancyId)

    }

}