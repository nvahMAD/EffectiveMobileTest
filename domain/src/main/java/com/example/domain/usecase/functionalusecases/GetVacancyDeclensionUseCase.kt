package com.example.domain.usecase.functionalusecases

import com.example.domain.repository.FunctionalRepository

class GetVacancyDeclensionUseCase(private val repository: FunctionalRepository) {
    fun execute(count: Int): String{
        return repository.getVacancyDeclension(count)
    }
}