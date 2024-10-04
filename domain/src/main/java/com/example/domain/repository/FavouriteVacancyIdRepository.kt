package com.example.domain.repository

import com.example.domain.model.FavouriteVacancyId
import io.reactivex.Completable
import io.reactivex.Flowable

interface FavouriteVacancyIdRepository {
    fun addFavouriteVacancy(favouriteVacancyId : FavouriteVacancyId) : Completable
    fun removeFavouriteVacancy(favouriteVacancyId: FavouriteVacancyId) : Completable
    fun getAllFavouriteVacancies(): Flowable<List<FavouriteVacancyId>>
}