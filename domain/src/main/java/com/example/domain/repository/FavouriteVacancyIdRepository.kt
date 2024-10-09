package com.example.domain.repository

import com.example.domain.model.apimodels.Vacancy
import com.example.domain.model.favouritesmodels.FavouriteVacancyId
import io.reactivex.Completable
import io.reactivex.Single

interface FavouriteVacancyIdRepository {
    fun addFavouriteVacancyId(favouriteVacancyId: FavouriteVacancyId): Completable
    fun removeFavouriteVacancyId(favouriteVacancyId: FavouriteVacancyId): Completable
    fun getAllFavouriteVacanciesIds(): Single<List<FavouriteVacancyId>>
    fun getAllFavouritesVacancies(vacancies: List<Vacancy>): Single<List<Vacancy>>
}