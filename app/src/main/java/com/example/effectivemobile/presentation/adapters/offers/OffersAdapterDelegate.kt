package com.example.effectivemobile.presentation.adapters.offers

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.apimodels.Offer
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.OfferItemBinding
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class OffersAdapterDelegate : AdapterDelegate<List<Offer>>() {

    override fun isForViewType(items: List<Offer>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): OffersViewHolder {
        val binding = OfferItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OffersViewHolder(binding)
    }

    override fun onBindViewHolder(
        items: List<Offer>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val offer = items[position]
        (holder as OffersViewHolder).bind(offer)
    }


    class OffersViewHolder(private val binding: OfferItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(offer: Offer) {
            binding.offerTitle.text = offer.title

            if (offer.id != null) {
                when (offer.id) {
                    "near_vacancies" -> {
                        binding.offerImageBackground.setCardBackgroundColor(
                            ContextCompat.getColor(
                                itemView.context,
                                R.color.special_dark_blue
                            )
                        )
                        binding.offerImage.setImageResource(R.drawable.nearby_vacancies_icon)
                    }

                    "level_up_resume" -> {
                        binding.offerImageBackground.setCardBackgroundColor(
                            ContextCompat.getColor(
                                itemView.context,
                                R.color.special_dark_green
                            )
                        )
                        binding.offerImage.setImageResource(R.drawable.level_up_resume_icon)

                    }

                    "temporary_job" -> {
                        binding.offerImageBackground.setCardBackgroundColor(
                            ContextCompat.getColor(
                                itemView.context,
                                R.color.special_dark_green
                            )
                        )
                        binding.offerImage.setImageResource(R.drawable.temporary_job_icon)

                    }
                }
            } else {
                binding.offerImageBackground.visibility = View.GONE
            }

            binding.offerCard.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(offer.link)
                itemView.context.startActivity(intent)
            }

            if (offer.button != null) {
                binding.offerButton.visibility = View.VISIBLE
                binding.offerButton.text = offer.button.text
                binding.offerTitle.maxLines = 2
            }
        }
    }
}