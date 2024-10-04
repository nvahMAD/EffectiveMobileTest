package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favouritesVacancies")
data class FavouriteVacancyEntity(
    @PrimaryKey(autoGenerate = false) val value : String
)
