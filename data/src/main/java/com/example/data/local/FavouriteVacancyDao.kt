package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface FavouriteVacancyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favouriteVacancyEntity: FavouriteVacancyEntity)

    @Query("DELETE FROM favouritesVacancies WHERE value = :value")
    fun deleteByValue(value: String)

    @Query("SELECT * FROM favouritesVacancies")
    fun getAllFavouritesVacancies(): Flowable<List<FavouriteVacancyEntity>>
}