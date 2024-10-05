package com.example.data.repository

import com.example.data.local.FavouriteVacancyDao
import com.example.data.local.FavouriteVacancyEntity
import com.example.domain.model.favouritesmodels.FavouriteVacancyId
import com.example.domain.repository.FavouriteVacancyIdRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class FavouriteVacancyIdRepositoryImpl (private val favouriteVacancyDao: FavouriteVacancyDao) :
    FavouriteVacancyIdRepository {

    override fun addFavouriteVacancy(favouriteVacancyId: FavouriteVacancyId): Completable {
        return Completable.fromAction {
            favouriteVacancyDao.insert(FavouriteVacancyEntity(value = favouriteVacancyId.value))
        }.subscribeOn(Schedulers.io())
    }

    override fun removeFavouriteVacancy(favouriteVacancyId: FavouriteVacancyId): Completable {
        return Completable.fromAction {
            favouriteVacancyDao.deleteByValue(favouriteVacancyId.value)
        }.subscribeOn(Schedulers.io())
    }

    override fun getAllFavouriteVacancies(): Flowable<List<FavouriteVacancyId>> {
        return favouriteVacancyDao.getAllFavouritesVacancies()
            .map { entities -> entities.map { FavouriteVacancyId(value = it.value) } }
            .subscribeOn(Schedulers.io())
    }

}