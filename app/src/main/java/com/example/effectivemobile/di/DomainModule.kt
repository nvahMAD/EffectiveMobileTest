package com.example.effectivemobile.di

import com.example.domain.repository.ApiResponseRepository
import com.example.domain.repository.FavouriteVacancyIdRepository
import com.example.domain.repository.FunctionalRepository
import com.example.domain.usecase.apiusecases.GetApiResponseUseCase
import com.example.domain.usecase.functionalusecases.GetMonthNameUseCase
import com.example.domain.usecase.functionalusecases.GetPeopleDeclensionUseCase
import com.example.domain.usecase.functionalusecases.GetVacancyDeclensionUseCase
import com.example.domain.usecase.savedfavourites.AddFavouriteVacancyIdUseCase
import com.example.domain.usecase.savedfavourites.GetAllFavouritesIdsUseCase
import com.example.domain.usecase.savedfavourites.GetAllFavouritesVacanciesUseCase
import com.example.domain.usecase.savedfavourites.RemoveFavouriteVacancyIdUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetAllFavouritesUseCase(repository: FavouriteVacancyIdRepository): GetAllFavouritesIdsUseCase {
        return GetAllFavouritesIdsUseCase(repository)
    }

    @Provides
    fun provideAddFavouriteVacancyUseCase(repository: FavouriteVacancyIdRepository): AddFavouriteVacancyIdUseCase {
        return AddFavouriteVacancyIdUseCase(repository)
    }

    @Provides
    fun provideRemoveFavouriteVacancyUseCase(repository: FavouriteVacancyIdRepository): RemoveFavouriteVacancyIdUseCase {
        return RemoveFavouriteVacancyIdUseCase(repository)
    }

    @Provides
    fun provideGetApiResponseUseCase(repository: ApiResponseRepository): GetApiResponseUseCase {
        return GetApiResponseUseCase(repository)
    }

    @Provides
    fun provideGetAllFavouritesVacanciesUseCase(repository: FavouriteVacancyIdRepository): GetAllFavouritesVacanciesUseCase {
        return GetAllFavouritesVacanciesUseCase(repository)
    }

    @Provides
    fun provideGetMonthNameUseCase(repository: FunctionalRepository): GetMonthNameUseCase {
        return GetMonthNameUseCase(repository)
    }

    @Provides
    fun provideGetPeopleDeclensionUseCase(repository: FunctionalRepository): GetPeopleDeclensionUseCase {
        return GetPeopleDeclensionUseCase(repository)
    }

    @Provides
    fun provideGetVacancyDeclensionUseCase(repository: FunctionalRepository): GetVacancyDeclensionUseCase {
        return GetVacancyDeclensionUseCase(repository)
    }

}