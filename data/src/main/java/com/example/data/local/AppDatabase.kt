package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavouriteVacancyEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteVacancyDAO(): FavouriteVacancyDao
}