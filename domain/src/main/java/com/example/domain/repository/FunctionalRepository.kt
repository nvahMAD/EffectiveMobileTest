package com.example.domain.repository

interface FunctionalRepository {
    fun getMonthName(monthNumber: Int): String
    fun getPeopleDeclension(count: Int): String
    fun getVacancyDeclension(count: Int): String
}