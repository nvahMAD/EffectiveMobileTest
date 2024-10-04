package com.example.effectivemobile.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.AppDatabase
import com.example.data.local.FavouriteVacancyDao
import com.example.data.repository.FavouriteVacancyIdRepositoryImpl
import com.example.domain.repository.FavouriteVacancyIdRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideAppDatabase( context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "favourites_vacancies_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavouriteVacancyDAO(appDatabase: AppDatabase): FavouriteVacancyDao{
        return appDatabase.favouriteVacancyDAO()
    }

    @Provides
    @Singleton
    fun provideFavouritesVacancyIdRepository(favouriteVacancyDao: FavouriteVacancyDao): FavouriteVacancyIdRepository{
        return FavouriteVacancyIdRepositoryImpl(favouriteVacancyDao)
    }
}