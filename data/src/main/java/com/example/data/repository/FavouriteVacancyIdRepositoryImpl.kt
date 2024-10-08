package com.example.data.repository

import com.example.data.local.FavouriteVacancyDao
import com.example.data.local.FavouriteVacancyEntity
import com.example.domain.model.apimodels.Vacancy
import com.example.domain.model.favouritesmodels.FavouriteVacancyId
import com.example.domain.repository.FavouriteVacancyIdRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class FavouriteVacancyIdRepositoryImpl(private val favouriteVacancyDao: FavouriteVacancyDao) :
    FavouriteVacancyIdRepository {

    override fun addFavouriteVacancyId(favouriteVacancyId: FavouriteVacancyId): Completable {
        return Completable.fromAction {
            favouriteVacancyDao.insert(FavouriteVacancyEntity(value = favouriteVacancyId.value))
        }.subscribeOn(Schedulers.io())
    }

    override fun removeFavouriteVacancyId(favouriteVacancyId: FavouriteVacancyId): Completable {
        return Completable.fromAction {
            favouriteVacancyDao.deleteByValue(favouriteVacancyId.value)
        }.subscribeOn(Schedulers.io())
    }

    override fun getAllFavouriteVacanciesIds(): Single<List<FavouriteVacancyId>> {
        return favouriteVacancyDao.getAllFavouritesVacancies()
            .map { entities -> entities.map { FavouriteVacancyId(value = it.value) } }
            .subscribeOn(Schedulers.io())
    }

    override fun getAllFavouritesVacancies(vacancies: List<Vacancy>): Single<List<Vacancy>> {
        return getAllFavouriteVacanciesIds().map { favouritesIds ->
            vacancies.filter { vacancy ->
                favouritesIds.any { it.value.contains(vacancy.id) }
            }
        }
    }


}