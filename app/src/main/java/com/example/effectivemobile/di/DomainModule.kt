package com.example.effectivemobile.di

import com.example.domain.repository.FavouriteVacancyIdRepository
import com.example.domain.usecase.savedfavourites.AddFavouriteVacancyUseCase
import com.example.domain.usecase.savedfavourites.GetAllFavouritesUseCase
import com.example.domain.usecase.savedfavourites.RemoveFavouriteVacancyUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetAllFavouritesUseCase(repository: FavouriteVacancyIdRepository): GetAllFavouritesUseCase {
        return GetAllFavouritesUseCase(repository)
    }

    @Provides
    fun provideAddFavouriteVacancyUseCase(repository: FavouriteVacancyIdRepository): AddFavouriteVacancyUseCase {
        return AddFavouriteVacancyUseCase(repository)
    }

    @Provides
    fun provideRemoveFavouriteVacancyUseCase(repository: FavouriteVacancyIdRepository): RemoveFavouriteVacancyUseCase {
        return RemoveFavouriteVacancyUseCase(repository)
    }

}