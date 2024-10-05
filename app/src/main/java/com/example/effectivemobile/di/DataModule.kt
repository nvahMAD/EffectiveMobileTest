package com.example.effectivemobile.di

import android.content.Context
import androidx.room.Room
import com.example.data.api.ApiService
import com.example.data.local.AppDatabase
import com.example.data.local.FavouriteVacancyDao
import com.example.data.repository.ApiResponseRepositoryImpl
import com.example.data.repository.FavouriteVacancyIdRepositoryImpl
import com.example.domain.repository.ApiResponseRepository
import com.example.domain.repository.FavouriteVacancyIdRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiResponseRepository(apiService: ApiService) : ApiResponseRepository{
        return ApiResponseRepositoryImpl(apiService)
    }

}