package com.example.domain.repository

import com.example.domain.model.apimodels.ApiResponseModel
import io.reactivex.Single

interface ApiResponseRepository {
    fun getApiResponse(): Single<ApiResponseModel>
}