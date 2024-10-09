package com.example.data.repository

import com.example.domain.repository.FunctionalRepository

class FunctionalRepositoryImpl : FunctionalRepository {
    override fun getMonthName(monthNumber: Int): String {
        return when (monthNumber) {
            1 -> "Января"
            2 -> "Февраля"
            3 -> "Марта"
            4 -> "Апреля"
            5 -> "Мая"
            6 -> "Июня"
            7 -> "Июля"
            8 -> "Августа"
            9 -> "Сентября"
            10 -> "Октября"
            11 -> "Ноября"
            12 -> "Декабря"
            else -> throw IllegalArgumentException("Неверный номер месяца")
        }
    }

    override fun getPeopleDeclension(count: Int): String {
        return when {
            count % 10 == 1 && count % 100 != 11 -> "человек"
            count % 10 in 2..4 && count % 100 !in 12..14 -> "человека"
            else -> "человек"
        }
    }

    override fun getVacancyDeclension(count: Int): String {
        val lastDigit = count % 10
        val lastTwoDigits = count % 100

        return when {
            lastTwoDigits in 11..14 -> "$count вакансий"
            lastDigit == 1 -> "$count вакансия"
            lastDigit in 2..4 -> "$count вакансии"
            else -> "$count вакансий"
        }
    }

}