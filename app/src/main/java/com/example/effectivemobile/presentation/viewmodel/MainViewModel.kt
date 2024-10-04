package com.example.effectivemobile.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.FavouriteVacancyId
import com.example.domain.usecase.savedfavourites.AddFavouriteVacancyUseCase
import com.example.domain.usecase.savedfavourites.GetAllFavouritesUseCase
import com.example.domain.usecase.savedfavourites.RemoveFavouriteVacancyUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val addFavouriteVacancyUseCase: AddFavouriteVacancyUseCase,
    private val removeFavouriteVacancyUseCase: RemoveFavouriteVacancyUseCase,
    private val getAllFavouritesUseCase: GetAllFavouritesUseCase
) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val _favouritesLiveData = MutableLiveData<List<FavouriteVacancyId>>()
    val favouritesLiveData: LiveData<List<FavouriteVacancyId>> get() = _favouritesLiveData

    @SuppressLint("CheckResult")
    fun getAllStrings() {
        getAllFavouritesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { strings ->
                _favouritesLiveData.value = strings
            }
    }

    fun addFavourites(favouriteId: String){
        val item = FavouriteVacancyId(value = favouriteId)
        disposable.add(
            addFavouriteVacancyUseCase.execute(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    fun removeFavourites(favouriteId: String){
        val item = FavouriteVacancyId(value = favouriteId)
        disposable.add(
            removeFavouriteVacancyUseCase.execute(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}