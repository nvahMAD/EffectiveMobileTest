package com.example.data.api

import com.example.domain.model.apimodels.ApiResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    fun getResponse(
        @Url url: String = "https://drive.usercontent.google.com/u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download"
    ): Single<ApiResponseModel>

}