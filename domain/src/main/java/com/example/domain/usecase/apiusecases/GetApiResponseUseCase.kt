package com.example.domain.usecase.apiusecases

import com.example.domain.model.apimodels.ApiResponseModel
import com.example.domain.repository.ApiResponseRepository
import io.reactivex.Single

class GetApiResponseUseCase(private val apiResponseRepository: ApiResponseRepository) {

    fun execute(): Single<ApiResponseModel> {
        return apiResponseRepository.getApiResponse()
    }

}