package com.example.effectivemobile.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.apimodels.ApiResponseModel
import com.example.domain.model.apimodels.Vacancy
import com.example.domain.model.favouritesmodels.FavouriteVacancyId
import com.example.domain.repository.ApiResponseRepository
import com.example.domain.usecase.apiusecases.GetApiResponseUseCase
import com.example.domain.usecase.functionalusecases.GetMonthNameUseCase
import com.example.domain.usecase.functionalusecases.GetPeopleDeclensionUseCase
import com.example.domain.usecase.functionalusecases.GetVacancyDeclensionUseCase
import com.example.domain.usecase.savedfavourites.AddFavouriteVacancyIdUseCase
import com.example.domain.usecase.savedfavourites.GetAllFavouritesIdsUseCase
import com.example.domain.usecase.savedfavourites.GetAllFavouritesVacanciesUseCase
import com.example.domain.usecase.savedfavourites.RemoveFavouriteVacancyIdUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val addFavouriteVacancyIdUseCase: AddFavouriteVacancyIdUseCase,
    private val removeFavouriteVacancyIdUseCase: RemoveFavouriteVacancyIdUseCase,
    private val getAllFavouritesIdsUseCase: GetAllFavouritesIdsUseCase,
    private val getApiResponseUseCase: GetApiResponseUseCase,
    private val apiResponseRepository: ApiResponseRepository,
    private val getAllFavouritesVacanciesUseCase: GetAllFavouritesVacanciesUseCase,
    private val getMonthNameUseCase: GetMonthNameUseCase,
    private val getPeopleDeclensionUseCase: GetPeopleDeclensionUseCase,
    private val getVacancyDeclensionUseCase: GetVacancyDeclensionUseCase

) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _favouritesLiveData = MutableLiveData<List<Vacancy>>()
    val favouritesLiveData: LiveData<List<Vacancy>> get() = _favouritesLiveData

    private val _apiResponseData = MutableLiveData<ApiResponseModel>()
    val apiResponseData: LiveData<ApiResponseModel> get() = _apiResponseData

    private val _apiError = MutableLiveData<String>()
    val apiError: LiveData<String> get() = _apiError

    private val _favouriteCountLiveData = MutableLiveData<Int>()
    val favouriteCountLiveData: LiveData<Int> get() = _favouriteCountLiveData

    private val _favouritesIdsLiveData = MutableLiveData<List<String>>()
    val favouritesIdsLiveData: LiveData<List<String>> get() = _favouritesIdsLiveData

    init {
        getAllFavourites()
        getApiResponse()
        getFavouritesIds()
    }

    fun getVacancyDeclension(count: Int): String {
        return getVacancyDeclensionUseCase.execute(count)
    }

    fun getPeopleDeclension(count: Int): String {
        return getPeopleDeclensionUseCase.execute(count)
    }

    fun getMonthName(monthNumber: Int): String {
        return getMonthNameUseCase.execute(monthNumber)
    }

    @SuppressLint("CheckResult")
    fun getFavouritesIds() {
        getAllFavouritesIdsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { ids ->
                _favouritesIdsLiveData.postValue(ids.map { it.value })
                _favouriteCountLiveData.postValue(ids.size)
            }
    }

    fun onClickVacancyInSearchFragment(vacancy: Vacancy) {
        val currentFavouriteVacancies: MutableList<Vacancy> =
            _favouritesLiveData.value?.toMutableList() ?: mutableListOf()
        val currentCount = _favouriteCountLiveData.value ?: 0
        if (!currentFavouriteVacancies.contains(vacancy)) {
            _favouriteCountLiveData.value = currentCount + 1
            currentFavouriteVacancies.add(vacancy)
            _favouritesLiveData.value = currentFavouriteVacancies
            addFavourites(vacancy)
        } else {
            _favouriteCountLiveData.value = currentCount - 1
            currentFavouriteVacancies.remove(vacancy)
            _favouritesLiveData.value = currentFavouriteVacancies
            removeFavourites(vacancy)
        }
    }

    fun onClickVacancyInFavouritesFragment(vacancy: Vacancy) {
        val currentCount = _favouriteCountLiveData.value ?: 0
        _favouriteCountLiveData.value = currentCount - 1
        removeFavourites(vacancy)
    }


    @SuppressLint("CheckResult")
    fun getAllFavourites() {
        apiResponseRepository.getApiResponse()
            .flatMap { response ->
                getAllFavouritesVacanciesUseCase.execute(response.vacancies)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ favouriteVacancies ->
                _favouritesLiveData.postValue(favouriteVacancies)
            }, {
                Log.e("MainViewModel", "Ошибка загрузки и фильтрации вакансий: ${it.message}")
            })
    }

    fun addFavourites(favouriteVacancy: Vacancy) {
        val item = FavouriteVacancyId(value = favouriteVacancy.id)
        disposable.add(
            addFavouriteVacancyIdUseCase.execute(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )

    }

    private fun removeFavourites(favouriteVacancy: Vacancy) {
        val item = FavouriteVacancyId(value = favouriteVacancy.id)
        disposable.add(
            removeFavouriteVacancyIdUseCase.execute(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    @SuppressLint("CheckResult")
    fun getApiResponse() {
        getApiResponseUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ item ->
                _apiResponseData.postValue(item)
            }, {
                _apiError.postValue(it.message)
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}