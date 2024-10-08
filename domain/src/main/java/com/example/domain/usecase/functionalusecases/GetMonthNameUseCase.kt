package com.example.domain.usecase.functionalusecases

import com.example.domain.repository.FunctionalRepository

class GetMonthNameUseCase(private val repository: FunctionalRepository) {
    fun execute(monthNumber: Int) : String{
        return repository.getMonthName(monthNumber)
    }
}