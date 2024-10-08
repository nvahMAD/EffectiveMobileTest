package com.example.effectivemobile.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.apimodels.Vacancy
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.VacancyItemBinding

class VacanciesAdapter(
    private val allVacancies: MutableList<Vacancy>,
    private val favouritesVacancies: List<String>,
    private val monthName: (Int) -> String,
    private val peopleDeclension: (Int) -> String,
    private val onVacancyClick: (Vacancy) -> Boolean
) : RecyclerView.Adapter<VacanciesAdapter.ViewHolder>() {

    private lateinit var binding: VacancyItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = VacancyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int = allVacancies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var currentFavouriteRecource: Int = 0

        if (favouritesVacancies.contains(allVacancies[position].id)) {
            currentFavouriteRecource = R.drawable.favourite_icon
            binding.toFavouriteButton.setImageResource(currentFavouriteRecource)
        } else {
            currentFavouriteRecource = R.drawable.unfavourite_icon
            binding.toFavouriteButton.setImageResource(currentFavouriteRecource)
        }

        if (allVacancies[position].lookingNumber == 0){
            binding.nowLookingTextView.visibility = View.INVISIBLE
        }

        binding.vacancyTitleTextView.text = allVacancies[position].title
        binding.vacancyAddressTextView.text = allVacancies[position].address.town
        binding.nowLookingTextView.text =
            "Сейчас просматривает ${allVacancies[position].lookingNumber} ${peopleDeclension(allVacancies[position].lookingNumber)}"
        binding.vacancyCompanyTitleTextView.text = allVacancies[position].company
        binding.experienceTitle.text = allVacancies[position].experience.previewText
        if (allVacancies[position].salary.full != "Уровень дохода не указан") {
            binding.salaryTextView.text =
                allVacancies[position].salary.short.replace("от ", "").replace(" до ", "-")
        } else {
            binding.salaryTextView.visibility = View.GONE
        }
        binding.publicDate.text =
            "Опубликовано ${allVacancies[position].publishedDate.split("-")[2]} ${
                monthName(allVacancies[position].publishedDate.split("-")[1].toInt())
            }"

        binding.toFavouriteButton.setOnClickListener { v ->
            val imageView = v as ImageView
            if (currentFavouriteRecource == R.drawable.favourite_icon) {
                currentFavouriteRecource = R.drawable.unfavourite_icon
                imageView.setImageResource(currentFavouriteRecource)
            } else {
                currentFavouriteRecource = R.drawable.favourite_icon
                imageView.setImageResource(currentFavouriteRecource)
            }
            if (onVacancyClick(allVacancies[position])){
                allVacancies.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, allVacancies.size)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}