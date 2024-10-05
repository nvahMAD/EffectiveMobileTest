package com.example.domain.model.apimodels

data class ApiResponseModel(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>
)