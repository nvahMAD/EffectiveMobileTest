package com.example.data.repository

import com.example.data.api.ApiService
import com.example.domain.model.apimodels.ApiResponseModel
import com.example.domain.repository.ApiResponseRepository
import io.reactivex.Single

class ApiResponseRepositoryImpl(private val apiService: ApiService): ApiResponseRepository {

    override fun getApiResponse(): Single<ApiResponseModel> {
        return apiService.getResponse()
    }


}