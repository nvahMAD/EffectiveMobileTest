package com.example.effectivemobile.presentation.adapters.vacancies

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.apimodels.Vacancy
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class VacanciesAdapter(
    private val allVacancies: MutableList<Vacancy>,
    private val favouritesVacancies: MutableList<String>,
    private val monthName: (Int) -> String,
    private val peopleDeclension: (Int) -> String,
    private val isOnFavouriteFragment: Boolean,
    private val onFavouriteClick: (Vacancy) -> Unit,
    private val onVacancyClick: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val delegatesManager = AdapterDelegatesManager<MutableList<Vacancy>>()

    init {
        delegatesManager.addDelegate(
            VacancyAdapterDelegate(
                favouritesVacancies,
                monthName,
                peopleDeclension,
                { clickedVacancy, clickedPosition ->
                    onVacancyClick(
                        clickedVacancy,
                        clickedPosition
                    )
                }, onVacancyClick
            )
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(allVacancies, position, holder, null)
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(allVacancies, position)
    }

    override fun getItemCount(): Int = allVacancies.size

    private fun onVacancyClick(clickedVacancy: Vacancy, position: Int) {
        if (isOnFavouriteFragment) {
            allVacancies.remove(clickedVacancy)
            notifyItemRemoved(position)
        }
        onFavouriteClick(clickedVacancy)
    }


}