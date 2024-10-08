package com.example.effectivemobile.presentation.adapters

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

class OffersAdapter(private val offers: List<Offer>) :
    RecyclerView.Adapter<OffersAdapter.ViewHolder>() {

    private lateinit var binding: OfferItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = OfferItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int = offers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        binding.offerTitle.text = offers[position].title

        if (offers[position].id != null) {
            when (offers[position].id) {
                "near_vacancies" -> {
                    binding.offerImageBackground.setCardBackgroundColor(
                        ContextCompat.getColor(
                            holder.itemView.context,
                            R.color.darkBlue
                        )
                    )
                    binding.offerImage.setImageResource(R.drawable.nearby_vacancies_icon)
                }

                "level_up_resume" -> {
                    binding.offerImageBackground.setCardBackgroundColor(
                        ContextCompat.getColor(
                            holder.itemView.context,
                            R.color.darkGreen
                        )
                    )
                    binding.offerImage.setImageResource(R.drawable.level_up_resume_icon)

                }

                "temporary_job" -> {
                    binding.offerImageBackground.setCardBackgroundColor(
                        ContextCompat.getColor(
                            holder.itemView.context,
                            R.color.darkGreen
                        )
                    )
                    binding.offerImage.setImageResource(R.drawable.temporary_job_icon)

                }
            }
        }
        else{
            binding.offerImageBackground.visibility = View.GONE
        }

        binding.offerCard.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(offers[position].link)
            holder.itemView.context.startActivity(intent)
        }

        if (offers[position].button != null) {
            binding.offerButton.visibility = View.VISIBLE
            binding.offerButton.text = offers[position].button.text
            binding.offerTitle.maxLines = 2
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}