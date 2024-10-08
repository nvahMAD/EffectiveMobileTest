package com.example.domain.usecase.savedfavourites

import com.example.domain.model.apimodels.Vacancy
import com.example.domain.repository.FavouriteVacancyIdRepository
import io.reactivex.Single

class GetAllFavouritesVacanciesUseCase(private val repository: FavouriteVacancyIdRepository) {
    fun execute(vacancies: List<Vacancy>): Single<List<Vacancy>>{
        return repository.getAllFavouritesVacancies(vacancies)
    }
}