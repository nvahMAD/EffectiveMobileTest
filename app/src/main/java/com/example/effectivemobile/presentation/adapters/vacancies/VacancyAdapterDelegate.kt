package com.example.effectivemobile.presentation.adapters.vacancies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.apimodels.Vacancy
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.VacancyItemBinding
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class VacancyAdapterDelegate(
    private val favouritesVacancies: List<String>,
    private val monthName: (Int) -> String,
    private val peopleDeclension: (Int) -> String,
    private val onFavouriteClick: (Vacancy, Int) -> Unit,
    private val onVacancyClick: () -> Unit
) :
    AdapterDelegate<MutableList<Vacancy>>() {
    override fun isForViewType(items: MutableList<Vacancy>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): VacancyViewHolder {
        val binding = VacancyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacancyViewHolder(binding)
    }

    override fun onBindViewHolder(
        items: MutableList<Vacancy>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {

        val vacancy = items[position]
        (holder as VacancyViewHolder).bind(
            vacancy,
            favouritesVacancies,
            monthName,
            peopleDeclension,
            onFavouriteClick,
            onVacancyClick
        )

    }


    class VacancyViewHolder(private val binding: VacancyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            vacancy: Vacancy,
            favouriteVacancies: List<String>,
            monthName: (Int) -> String,
            peopleDeclension: (Int) -> String,
            onFavouriteClick: (Vacancy, Int) -> Unit,
            onVacancyClick: () -> Unit
        ) {
            var currentFavouriteResource: Int = 0

            if (favouriteVacancies.contains(vacancy.id)) {
                currentFavouriteResource = R.drawable.favourite_icon
                binding.toFavouriteButton.setImageResource(currentFavouriteResource)
            } else {
                currentFavouriteResource = R.drawable.unfavourite_icon
                binding.toFavouriteButton.setImageResource(currentFavouriteResource)
            }

            if (vacancy.lookingNumber == 0) {
                binding.nowLookingTextView.visibility = View.INVISIBLE
            }

            binding.vacancyTitleTextView.text = vacancy.title
            binding.vacancyAddressTextView.text = vacancy.address.town
            binding.nowLookingTextView.text =
                "Сейчас просматривает ${vacancy.lookingNumber} ${peopleDeclension(vacancy.lookingNumber)}"
            binding.vacancyCompanyTitleTextView.text = vacancy.company
            binding.experienceTitle.text = vacancy.experience.previewText
            if (vacancy.salary.full != "Уровень дохода не указан") {
                binding.salaryTextView.text =
                    vacancy.salary.short.replace("от ", "").replace(" до ", "-")
            } else {
                binding.salaryTextView.visibility = View.GONE
            }
            binding.publicDate.text =
                "Опубликовано ${vacancy.publishedDate.split("-")[2]} ${
                    monthName(vacancy.publishedDate.split("-")[1].toInt())
                }"

            binding.vacancyCardView.setOnClickListener {
                onVacancyClick()
            }

            binding.toFavouriteButton.setOnClickListener { v ->
                val imageView = v as ImageView
                if (currentFavouriteResource == R.drawable.favourite_icon) {
                    currentFavouriteResource = R.drawable.unfavourite_icon
                    imageView.setImageResource(currentFavouriteResource)
                } else {
                    currentFavouriteResource = R.drawable.favourite_icon
                    imageView.setImageResource(currentFavouriteResource)
                }
                onFavouriteClick(vacancy, position)
            }
        }
    }
}